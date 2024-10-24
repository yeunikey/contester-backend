package me.yeunikey.contester.repositories.profile;

import me.yeunikey.contester.entities.profile.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
}
