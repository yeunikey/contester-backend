package me.yeunikey.contester.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.yeunikey.contester.ContesterApplication;
import me.yeunikey.contester.entities.AccountType;
import me.yeunikey.contester.entities.User;
import me.yeunikey.contester.entities.profile.Student;
import me.yeunikey.contester.entities.profile.Teacher;
import me.yeunikey.contester.repositories.UserRepository;
import me.yeunikey.contester.services.profile.StudentService;
import me.yeunikey.contester.services.profile.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, String, UserRepository> implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    public Gson gson() {
        return ContesterApplication.gson();
    }

    public JsonObject asJson(User user) {
        JsonObject json = gson().toJsonTree(user).getAsJsonObject();
        AccountType type = user.getType();

        if (type == AccountType.STUDENT) {
            Student student = studentService.find(user.getProfileId());
            json.add("profile", gson().toJsonTree(student).getAsJsonObject());
        } else {
            Teacher teacher = teacherService.find(user.getProfileId());
            json.add("profile", gson().toJsonTree(teacher).getAsJsonObject());
        }

        return json;
    }

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return save(user);
    }

    public boolean matchPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return find(username);
    }

}
