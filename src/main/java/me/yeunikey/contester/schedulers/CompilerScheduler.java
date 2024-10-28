package me.yeunikey.contester.schedulers;

import jakarta.transaction.Transactional;
import me.yeunikey.contester.entities.Attempt;
import me.yeunikey.contester.entities.assignments.Code;
import me.yeunikey.contester.entities.assignments.CodeStatus;
import me.yeunikey.contester.entities.assignments.Problem;
import me.yeunikey.contester.entities.assignments.Test;
import me.yeunikey.contester.services.assignments.AttemptService;
import me.yeunikey.contester.services.assignments.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.*;

@Component
public class CompilerScheduler {

    private static final int MAX_ATTEMPTS = 3;

    @Autowired
    private AttemptService attemptService;
    @Autowired
    private CodeService codeService;

    private Map<String, Attempt> queueAttempts = new HashMap<>();

    @Scheduled(fixedRate = 3000)
    public void attemptHandler() {

        int currentAttempts = queueAttempts.size();
        Queue<Attempt> attempts = new LinkedList<>(attemptService.findByStatus(CodeStatus.NOT_CHECKED));

        if (currentAttempts < MAX_ATTEMPTS) {
            for (int i = 0; i < MAX_ATTEMPTS - currentAttempts; i++) {
                Attempt attempt = attempts.poll();
                if (attempt == null) continue;
                queueAttempts.put(attempt.getUniqueId(), attempt);
            }
        }

        for (Attempt attempt : queueAttempts.values()) {
            proccessAttempt(attempt);
        }

    }

    @Transactional
    public void proccessAttempt(Attempt attempt) {
        try {
            codeService.createFile(attempt.getCode());

            int completedTests = 0;
            for (Test test : attempt.getProblem().getTests()) {
                boolean status = runTest(attempt.getProblem(), attempt.getCode(), test);

                if (!status) continue;

                completedTests++;
            }

            if (completedTests == attempt.getProblem().getTests().size()) {
                attempt.getCode().setStatus(CodeStatus.ACCEPTED);
            } else {
                attempt.getCode().setStatus(CodeStatus.WRONG_ANSWER);
            }

            System.out.println("Tests completed - " + completedTests + ", status - " + attempt.getCode().getStatus());

            codeService.save(attempt.getCode());
            queueAttempts.remove(attempt.getUniqueId());

            codeService.removeFile(attempt.getCode());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean runTest(Problem problem, Code code, Test test) {

        String path = "codes/" + code.getUniqueId() + ".py";
        ProcessBuilder processBuilder = new ProcessBuilder("python", path);
        processBuilder.redirectErrorStream(true);

        final long TIME_LIMIT_MS = problem.getLimits().getTimeLimit();
        final int MEMORY_LIMIT_MB = 64;

        try {
            Process process = processBuilder.start();

            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<Boolean> future = executor.submit(() -> {

                try (OutputStream os = process.getOutputStream();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

                    for (String input : test.getInput()) {
                        for (String splitted : input.split(" ")) {
                            os.write((splitted + "\n").getBytes());
                        }
                    }
                    os.flush();

                    List<String> outputs = new ArrayList<>();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        outputs.add(line);
                    }

                    int exitCode = process.waitFor();
                    if (exitCode == 0 && outputs.size() == test.getOutput().size()) {
                        for (int i = 0; i < outputs.size(); i++) {
                            if (!outputs.get(i).equals(test.getOutput().get(i))) {
                                return false;
                            }
                        }
                        return true;
                    } else {
                        return false;
                    }
                }
            });

            try {
                return future.get(TIME_LIMIT_MS, TimeUnit.MILLISECONDS);
            } catch (TimeoutException e) {
                process.destroyForcibly();
                System.out.println("Test: TIME OUT");
                return false;
            } finally {
                executor.shutdownNow();
            }

        } catch (Exception e) {
            System.out.println("Test: TEST FAILED - " + e.getMessage());
            return false;
        }
    }

}
