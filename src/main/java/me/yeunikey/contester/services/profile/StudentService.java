package me.yeunikey.contester.services.profile;

import me.yeunikey.contester.entities.profile.Student;
import me.yeunikey.contester.repositories.profile.StudentRepository;
import me.yeunikey.contester.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends BaseService<Student, String, StudentRepository> {

}
