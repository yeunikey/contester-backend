package me.yeunikey.contester.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.yeunikey.contester.ContesterApplication;
import me.yeunikey.contester.controller.requests.WeekCreateRequest;
import me.yeunikey.contester.entities.Role;
import me.yeunikey.contester.entities.User;
import me.yeunikey.contester.entities.assignments.Week;
import me.yeunikey.contester.services.assignments.WeekService;
import me.yeunikey.contester.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(path = "/v1/week")
@CrossOrigin(allowedHeaders = "*")
public class WeekController {

    @Autowired
    private WeekService weekService;

    private Gson gson() {
        return ContesterApplication.gson();
    }

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    @GetMapping(path = "/all")
    public ResponseEntity<String> all() {

        ResponseBuilder response = ResponseBuilder.builder();
        List<JsonObject> problemsJson = weekService.findAll()
                .stream()
                .map(week -> weekService.asJson(week, false))
                .toList();

        return response
                .success()
                .data(problemsJson)
                .build();
    }

    @GetMapping(path = "/get")
    public ResponseEntity<String> get(@RequestParam String weekId, @RequestParam boolean detailed) {

        ResponseBuilder response = ResponseBuilder.builder();
        Week week = weekService.find(weekId);

        if (week == null) {
            return response
                    .error()
                    .message("Week not found")
                    .build();
        }

        JsonObject weekJson = weekService.asJson(week, detailed);

        return response
                .success()
                .data(weekJson)
                .build();
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> delete(@RequestParam String weekId) {

        ResponseBuilder response = ResponseBuilder.builder();
        User user = getUser();

        if (user.getRole() != Role.ADMIN) {
            return response
                    .error()
                    .message("You need to have admin role for that")
                    .build();
        }

        Week week = weekService.find(weekId);

        if (week == null) {
            return response
                    .error()
                    .message("Week not found")
                    .build();
        }

        weekService.delete(week);

        return response
                .success()
                .message("Week deleted")
                .build();
    }

    @PostMapping(path = "/create")
    public ResponseEntity<String> create(@RequestBody WeekCreateRequest body) {

        ResponseBuilder response = ResponseBuilder.builder();
        User user = getUser();

        if (user.getRole() != Role.ADMIN) {
            return response
                    .error()
                    .message("You need to have admin role for that")
                    .build();
        }

        Week week = new Week(
                body.getName(),
                body.isClosed(),
                body.getCreatedDate(),
                body.getDeadlineDate()
        );

        JsonObject jsonObject = weekService.asJson(weekService.save(week), false);

        return response
                .success()
                .data(jsonObject)
                .build();
    }

}
