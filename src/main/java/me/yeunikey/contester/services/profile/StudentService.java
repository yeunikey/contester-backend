package me.yeunikey.contester.services.profile;

import me.yeunikey.contester.entities.profile.Student;
import me.yeunikey.contester.repositories.profile.StudentRepository;
import me.yeunikey.contester.services.BaseService;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends BaseService<Student, String, StudentRepository> {

}
