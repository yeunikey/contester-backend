package me.yeunikey.contester.services.assignments;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import me.yeunikey.contester.entities.assignments.Week;
import me.yeunikey.contester.repositories.WeekRepository;
import me.yeunikey.contester.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class WeekService extends BaseService<Week, String, WeekRepository> {

    @Autowired
    private ProblemService problemService;

    public JsonObject asJson(Week week, boolean detailedProblems) {

        JsonObject weekJson = new JsonObject();

        weekJson.addProperty("uniqueId", week.getUniqueId());
        weekJson.addProperty("name", week.getName());
        weekJson.addProperty("closed", week.isClosed());

        weekJson.addProperty("startDate", week.getStartDate().toString());
        weekJson.addProperty("deadlineDate", week.getDeadlineDate().toString());

        JsonArray problemsArray = new JsonArray();

        if (!detailedProblems) {
            problemsArray = week.getProblems()
                    .stream()
                    .map(problem -> new JsonPrimitive(problem.getUniqueId()))
                    .collect(JsonArray::new, JsonArray::add, JsonArray::addAll);
        } else {
            problemsArray = week.getProblems()
                    .stream()
                    .map(problem -> problemService.asJson(problem))
                    .collect(JsonArray::new, JsonArray::add, JsonArray::addAll);
        }

        weekJson.add("problems", problemsArray);

        return weekJson;
    }

    @Override
    public void delete(Week object) {

        object.getProblems().forEach(problem -> {
            problemService.delete(problem);
        });

        repository().delete(object);

    }
}
