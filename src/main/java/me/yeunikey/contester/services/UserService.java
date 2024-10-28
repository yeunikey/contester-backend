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

    public Gson gson() {
        return ContesterApplication.gson();
    }

    public JsonObject asJson(User user) {
        AccountType type = user.getType();

        JsonObject profileObject = null;

        if (type == AccountType.STUDENT) {
            Student student = studentService.find(user.getProfileId());

            profileObject = studentService.asJson(student);
        } else {
//            Teacher teacher = teacherService.find(user.getProfileId());
            // add teacher dto
        }

        JsonObject userJson = new JsonObject();

        userJson.addProperty("uniqueId", user.getUniqueId());
        userJson.addProperty("type", user.getType().toString());
        userJson.addProperty("role", user.getRole().toString());

        userJson.add("profile", profileObject);

        return userJson;
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
