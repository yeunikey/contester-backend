package me.yeunikey.contester.repositories.assignments;

import me.yeunikey.contester.entities.Attempt;
import me.yeunikey.contester.entities.assignments.CodeStatus;
import me.yeunikey.contester.entities.assignments.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttemptRepository extends JpaRepository<Attempt, String> {

    @Query("SELECT a FROM Attempt a WHERE a.code.status = :status")
    List<Attempt> findByStatus(@Param("status") CodeStatus status);

    @Query("SELECT a FROM Attempt a WHERE a.problem.uniqueId = :problemId AND a.userId.uniqueId = :userId")
    List<Attempt> findByUserAndProblem(String userId, String problemId);

    @Query("SELECT a FROM Attempt a WHERE a.problem.uniqueId = :problemId")
    List<Attempt> findByProblem(String problemId);

}
