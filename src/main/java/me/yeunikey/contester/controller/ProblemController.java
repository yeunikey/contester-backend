package me.yeunikey.contester.controller;

import com.google.gson.JsonObject;
import me.yeunikey.contester.controller.requests.AttemptRequest;
import me.yeunikey.contester.entities.Attempt;
import me.yeunikey.contester.entities.User;
import me.yeunikey.contester.entities.assignments.Code;
import me.yeunikey.contester.entities.assignments.LanguageType;
import me.yeunikey.contester.entities.assignments.Problem;
import me.yeunikey.contester.services.assignments.AttemptService;
import me.yeunikey.contester.services.assignments.CodeService;
import me.yeunikey.contester.services.assignments.ProblemService;
import me.yeunikey.contester.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping(path = "/v1/problem")
@CrossOrigin(allowedHeaders = "*")
public class ProblemController {

    @Autowired
    private ProblemService problemService;
    @Autowired
    private CodeService codeService;
    @Autowired
    private AttemptService attemptService;

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    @GetMapping(path = "/get")
    public ResponseEntity<String> get(@RequestParam String problemId) {

        ResponseBuilder response = ResponseBuilder.builder();
        Problem problem = problemService.find(problemId);

        if (problem == null) {
            return response
                    .error()
                    .message("Problem not found")
                    .build();
        }

        JsonObject problemJson = problemService.asJson(problem);

        return response
                .success()
                .data(problemJson)
                .build();
    }

    @PostMapping(path = "/create-attempt")
    public ResponseEntity<String> create(
            @RequestBody AttemptRequest request
            ) throws IOException {

        ResponseBuilder response = ResponseBuilder.builder();

        User user = getUser();
        Problem problem = problemService.find(request.getProblemId());
        LanguageType languageType = LanguageType.getLanguage(request.getLanguage());

        if (problem == null) {
            return response
                    .error()
                    .message("Problem not found")
                    .build();
        }

        if (languageType == null) {
            return response
                    .error()
                    .message("Language not found")
                    .build();
        }

        Code codeObject = new Code(
                languageType,
                codeService.asBytes(request.getCode())
        );
        codeService.save(codeObject);

        Attempt attempt = new Attempt(
                user,
                problem,
                codeObject
        );
        attemptService.save(attempt);

        JsonObject attemptJson = attemptService.asJson(attempt);

        return response
                .success()
                .data(attemptJson)
                .build();
    }

    @GetMapping(path = "/attempt-code")
    public ResponseEntity<String> attemptCode(@RequestParam String attemptId) throws InterruptedException {

        ResponseBuilder response = ResponseBuilder.builder();
        Attempt attempt = attemptService.find(attemptId);

        if (attempt == null) {
            return response
                    .error()
                    .message("Attempt not found")
                    .build();
        }

        return response.
                success()
                .data(codeService.asArray(attempt.getCode()))
                .build();
    }

}
