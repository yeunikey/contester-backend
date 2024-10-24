package me.yeunikey.contester.repositories;

import me.yeunikey.contester.entities.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptRepository extends JpaRepository<Attempt, String> {
}
