package me.yeunikey.contester.controller;

import com.google.gson.Gson;
import me.yeunikey.contester.ContesterApplication;
import me.yeunikey.contester.controller.model.user.ChangePasswordReq;
import me.yeunikey.contester.entities.User;
import me.yeunikey.contester.services.UserService;
import me.yeunikey.contester.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/v1/user")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
                .data(userService.save(user))
                .build();
    }

}
