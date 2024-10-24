package me.yeunikey.contester.repositories;

import me.yeunikey.contester.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
}
