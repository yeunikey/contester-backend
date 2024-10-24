package me.yeunikey.contester.controller;

import com.google.gson.Gson;
import me.yeunikey.contester.ContesterApplication;
import me.yeunikey.contester.controller.model.login.LoginReq;
import me.yeunikey.contester.controller.model.login.LoginRes;
import me.yeunikey.contester.entities.User;
import me.yeunikey.contester.services.GroupService;
import me.yeunikey.contester.services.JwtService;
import me.yeunikey.contester.services.UserService;
import me.yeunikey.contester.services.profile.StudentService;
import me.yeunikey.contester.util.ResponseBuilder;
import me.yeunikey.contester.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/v1/auth")
public class AuthController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private JwtService jwtService;

    public Gson gson() {
        return ContesterApplication.gson();
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody String body) {

        ResponseBuilder response = ResponseBuilder.builder();
        LoginReq req = gson().fromJson(body, LoginReq.class);

        if (req.getUniqueId() == null || req.getPassword() == null) {
            return response
                    .error()
                    .message("Body must have `uniqueId` and `password` fields")
                    .build();
        }

        User user = userService.find(req.getUniqueId());

        if (user == null) {
            return response
                    .error()
                    .message("User with `uniqueId` = `" + req.getUniqueId() + "` not found")
                    .build();
        }

        if (!userService.matchPassword(user, req.getPassword())) {
            return response
                    .error()
                    .message("Incorrect password")
                    .build();
        }

        String token = jwtService.generateToken(user.getUniqueId());
        LoginRes res = new LoginRes(token);

        return response
                .status(ResponseStatus.SUCCESS)
                .data(res)
                .build();
    }

}
