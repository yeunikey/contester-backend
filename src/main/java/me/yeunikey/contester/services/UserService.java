package me.yeunikey.contester.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.yeunikey.contester.ContesterApplication;
import me.yeunikey.contester.entities.AccountType;
import me.yeunikey.contester.entities.User;
import me.yeunikey.contester.entities.dto.ProfileDTO;
import me.yeunikey.contester.entities.dto.UserDTO;
import me.yeunikey.contester.entities.dto.profile.StudentDTO;
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
        AccountType type = user.getType();

        ProfileDTO profileDTO = null;

        if (type == AccountType.STUDENT) {
            Student student = studentService.find(user.getProfileId());

            profileDTO = new StudentDTO(
                    student.getUniqueId(),
                    student.getName(),
                    student.getSurname(),
                    student.getGroup().getGroupId(),
                    student.getTeacher() == null ? null : student.getTeacher().getUniqueId()
            );
        } else {
            Teacher teacher = teacherService.find(user.getProfileId());
            // add teacher dto
        }

        UserDTO userDTO = new UserDTO(
                user.getUniqueId(),
                user.getType(),
                profileDTO
        );

        return gson().toJsonTree(userDTO).getAsJsonObject();
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
