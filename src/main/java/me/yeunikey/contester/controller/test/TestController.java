package me.yeunikey.contester.controller.test;

import com.google.gson.Gson;
import me.yeunikey.contester.ContesterApplication;
import me.yeunikey.contester.entities.AccountType;
import me.yeunikey.contester.entities.Group;
import me.yeunikey.contester.entities.User;
import me.yeunikey.contester.entities.assignments.Limits;
import me.yeunikey.contester.entities.assignments.Problem;
import me.yeunikey.contester.entities.assignments.Test;
import me.yeunikey.contester.entities.assignments.Week;
import me.yeunikey.contester.entities.profile.Student;
import me.yeunikey.contester.services.GroupService;
import me.yeunikey.contester.services.UserService;
import me.yeunikey.contester.services.assignments.ProblemService;
import me.yeunikey.contester.services.assignments.WeekService;
import me.yeunikey.contester.services.profile.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Arrays;

@Controller
@RequestMapping(path = "/v1/test")
@CrossOrigin(allowedHeaders = "*")
public class TestController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private WeekService weekService;
    @Autowired
    private ProblemService problemService;

    public Gson gson() {
        return ContesterApplication.gson();
    }

    @GetMapping(path = "/group")
    public void group() {
        Group group = new Group(
                "SE-2401"
        );
        groupService.save(group);

        group = new Group(
                "SE-2402"
        );
        groupService.save(group);

        group = new Group(
                "SE-2403"
        );
        groupService.save(group);
    }

    @GetMapping(path = "/user")
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

    @GetMapping(path = "/weeks")
    public void test2() {
        Week week = new Week(
                "Week 04 [Lab]",
                false,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(21)
        );
        weekService.save(week);
    }

    @GetMapping(path = "/problem")
    public void problem() {
        Problem problem = new Problem(
                weekService.find("a0335448-6ee1-41d3-b193-c40e2819eb02"),
                "204. Problem 204",
                new Limits(2000, 65000),
                "Find and show the result of next expression: \"x² + y - z:2\", where 'z' is even number.\n",
                Arrays.asList(
                        new Test(Arrays.asList("3 4 2"), Arrays.asList("12"), false),
                        new Test(Arrays.asList("5 -5 10"), Arrays.asList("15"), false),
                        new Test(Arrays.asList("1 3 -4"), Arrays.asList("6"), false)
                )
        );
        problemService.save(problem);
    }

}
