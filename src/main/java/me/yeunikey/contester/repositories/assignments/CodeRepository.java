package me.yeunikey.contester.repositories.assignments;

import me.yeunikey.contester.entities.assignments.Code;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<Code, String> {
}
