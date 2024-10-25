package me.yeunikey.contester.entities.assignments;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Week {

    @Id
    private String uniqueId = UUID.randomUUID().toString();

    @Column(name = "name")
    private String name;
    @Column(name = "closed")
    private boolean closed;

    @Column(name = "startDate")
    private LocalDateTime startDate;
    @Column(name = "deadlineDate")
    private LocalDateTime deadlineDate;

    public Week() {
    }

    public Week(String name, boolean closed) {
        this.name = name;
        this.closed = closed;
    }

    public Week(String name, boolean closed, LocalDateTime startDate, LocalDateTime deadlineDate) {
        this.name = name;
        this.closed = closed;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDateTime deadlineDate) {
        this.deadlineDate = deadlineDate;
    }
}
