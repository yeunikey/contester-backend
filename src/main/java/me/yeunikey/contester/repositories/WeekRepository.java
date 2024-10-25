package me.yeunikey.contester.repositories;

import me.yeunikey.contester.entities.assignments.Week;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekRepository extends JpaRepository<Week, String> {
}
