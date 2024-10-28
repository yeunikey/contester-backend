package me.yeunikey.contester.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import me.yeunikey.contester.ContesterApplication;
import me.yeunikey.contester.controller.requests.user.ChangePasswordReq;
import me.yeunikey.contester.entities.Attempt;
import me.yeunikey.contester.entities.User;
import me.yeunikey.contester.entities.assignments.Problem;
import me.yeunikey.contester.services.UserService;
import me.yeunikey.contester.services.assignments.AttemptService;
import me.yeunikey.contester.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/v1/user")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AttemptService attemptService;

    public Gson gson() {
        return ContesterApplication.gson();
    }

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    @GetMapping(path = "/get")
    public ResponseEntity<String> get() {

        ResponseBuilder response = ResponseBuilder.builder();
        User user = getUser();

        return response
                .success()
                .data(userService.asJson(user))
                .build();
    }

    @PostMapping(path = "/change-password")
    public ResponseEntity<String> changePassword(@RequestBody String body) {

        ResponseBuilder response = ResponseBuilder.builder();
        ChangePasswordReq req = gson().fromJson(body, ChangePasswordReq.class);
        User user = getUser();

        user.setPassword(passwordEncoder.encode(req.getNewPassword()));

        return response
                .success()
                .build();
    }

    @GetMapping(path = "/attempts")
    public ResponseEntity<String> attempts(@RequestParam String problemId) {

        ResponseBuilder response = ResponseBuilder.builder();
        User user = getUser();

        JsonArray attempts = attemptService.findByProblemId(user.getUniqueId(), problemId)
                .stream()
                .map(attempt -> attemptService.asJson(attempt))
                .collect(JsonArray::new, JsonArray::add, JsonArray::addAll);

        return response
                .success()
                .data(attempts)
                .build();
    }

}
