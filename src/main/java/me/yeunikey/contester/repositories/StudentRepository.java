package me.yeunikey.contester.repositories;

import me.yeunikey.contester.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {

}
