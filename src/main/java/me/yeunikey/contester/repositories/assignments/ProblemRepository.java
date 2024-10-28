package me.yeunikey.contester.repositories.assignments;

import me.yeunikey.contester.entities.assignments.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, String> {
}
