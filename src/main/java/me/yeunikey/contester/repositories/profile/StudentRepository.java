package me.yeunikey.contester.repositories.profile;

import me.yeunikey.contester.entities.profile.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {

}
