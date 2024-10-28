package me.yeunikey.contester.entities;

import jakarta.persistence.*;
import me.yeunikey.contester.entities.assignments.Code;
import me.yeunikey.contester.entities.assignments.Problem;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "attempts")
public class Attempt {

    @Id
    private String uniqueId = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "problemId")
    private Problem problem;
    @OneToOne
    @JoinColumn(name = "codeId")
    private Code code;

    @Column(name = "createdDate")
    private LocalDateTime createdDate = LocalDateTime.now();

    public Attempt() {
    }

    public Attempt(User userId, Problem problem, Code code) {
        this.userId = userId;
        this.problem = problem;
        this.code = code;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
