package me.yeunikey.contester.services.assignments;

import com.google.gson.JsonObject;
import jakarta.transaction.Transactional;
import me.yeunikey.contester.entities.Attempt;
import me.yeunikey.contester.entities.assignments.Code;
import me.yeunikey.contester.entities.assignments.CodeStatus;
import me.yeunikey.contester.repositories.assignments.AttemptRepository;
import me.yeunikey.contester.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttemptService extends BaseService<Attempt, String, AttemptRepository> {

    @Autowired
    private CodeService codeService;

    public JsonObject asJson(Attempt attempt) {

        JsonObject attemptJson = new JsonObject();

        attemptJson.addProperty("attemptId", attempt.getUniqueId());
        attemptJson.addProperty("userId", attempt.getUserId().getUniqueId());
        attemptJson.addProperty("problemId", attempt.getProblem().getUniqueId());
        attemptJson.addProperty("createdDate", attempt.getCreatedDate().toString());
        attemptJson.add("code", codeService.asJson(attempt.getCode()));

        return attemptJson;
    }

    @Transactional
    public List<Attempt> findByStatus(CodeStatus status) {
        return repository().findByStatus(status);
    }

    @Transactional
    public List<Attempt> findByProblemId(String userId, String problemId) {
        return repository().findByUserAndProblem(userId, problemId);
    }

    @Transactional
    public List<Attempt> findByProblemId(String problemId) {
        return repository().findByProblem(problemId);
    }

    @Override
    public void delete(Attempt object) {

        Code code = object.getCode();

        repository().delete(object);
        codeService.delete(code);
    }
}
