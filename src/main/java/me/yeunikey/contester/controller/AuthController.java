package me.yeunikey.contester.controller;

import com.google.gson.Gson;
import me.yeunikey.contester.ContesterApplication;
import me.yeunikey.contester.controller.model.auth.StudentRes;
import me.yeunikey.contester.controller.model.auth.login.LoginReq;
import me.yeunikey.contester.controller.model.auth.login.LoginRes;
import me.yeunikey.contester.entities.AccountType;
import me.yeunikey.contester.entities.Group;
import me.yeunikey.contester.entities.User;
import me.yeunikey.contester.entities.profile.Student;
import me.yeunikey.contester.services.GroupService;
import me.yeunikey.contester.services.JwtService;
import me.yeunikey.contester.services.UserService;
import me.yeunikey.contester.services.profile.StudentService;
import me.yeunikey.contester.util.ResponseBuilder;
import me.yeunikey.contester.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/v1/auth")
@CrossOrigin(allowedHeaders = "*", origins = "*")
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

    @GetMapping(path = "/test")
    public void test() {
        Student student = new Student(
                "Ерасыл",
                "Унербек",
                groupService.find("SE-2402"),
                null
        );
        User user = new User(
                "1234",
                AccountType.STUDENT,
                student.getUniqueId()
        );
        student.setUser(user);
        studentService.save(student);
        userService.register(user);
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

    @GetMapping("/groups")
    public ResponseEntity<String> groups() {
        ResponseBuilder builder = ResponseBuilder.builder();

        List<String> groups = groupService.findAll().stream()
                .map(Group::getGroupId)
                .toList();

        return builder
                .success()
                .data(groups)
                .build();
    }

    @GetMapping("/students")
    public ResponseEntity<String> students(@RequestParam String group) {
        ResponseBuilder response = ResponseBuilder.builder();

        Group findedGroup = groupService.find(group);

        if (findedGroup == null) {
            return response
                    .error()
                    .message("Group not found")
                    .build();
        }

        List<StudentRes> students = findedGroup.getStudents()
                .stream()
                .map(student -> {
                    return new StudentRes(student.getUser().getUniqueId(), student.getName(), student.getSurname());
                })
                .toList();

        return response
                .success()
                .data(students)
                .build();
    }

}
