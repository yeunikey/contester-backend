package me.yeunikey.contester.services.assignments;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.yeunikey.contester.ContesterApplication;
import me.yeunikey.contester.entities.Attempt;
import me.yeunikey.contester.entities.assignments.Problem;
import me.yeunikey.contester.repositories.assignments.ProblemRepository;
import me.yeunikey.contester.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemService extends BaseService<Problem, String, ProblemRepository> {

    @Autowired
    private AttemptService attemptService;

    private Gson gson() {
        return ContesterApplication.gson();
    }

    public JsonObject asJson(Problem problem) {

        JsonObject problemJson = new JsonObject();

        problemJson.addProperty("uniqueId", problem.getUniqueId());
        problemJson.addProperty("weekId", problem.getWeekId().getUniqueId());
        problemJson.addProperty("title", problem.getTitle());
        problemJson.addProperty("lore", problem.getLore());

        problemJson.add("limits", gson().toJsonTree(problem.getLimits()).getAsJsonObject());
        problemJson.add("tests", gson().toJsonTree(problem.getTests().stream().filter(test -> !test.isHidden()).toList()).getAsJsonArray());

        return problemJson;
    }

    @Override
    public void delete(Problem object) {

        object.getTests().clear();
        save(object);

        List<Attempt> attempts = attemptService.findByProblemId(object.getUniqueId());
        attempts.forEach(attempt -> {
            attemptService.delete(attempt);
        });

        repository().delete(object);
    }
}
