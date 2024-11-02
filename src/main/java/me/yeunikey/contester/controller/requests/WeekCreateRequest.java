package me.yeunikey.contester.controller.requests;

import java.time.LocalDateTime;

public class WeekCreateRequest {

    private String name;
    private boolean closed;
    private LocalDateTime createdDate;
    private LocalDateTime deadlineDate;

    public WeekCreateRequest() {
    }

    public WeekCreateRequest(String name, boolean closed, LocalDateTime createdDate, LocalDateTime deadlineDate) {
        this.name = name;
        this.closed = closed;
        this.createdDate = createdDate;
        this.deadlineDate = deadlineDate;
    }

    public String getName() {
        return name;
    }

    public boolean isClosed() {
        return closed;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getDeadlineDate() {
        return deadlineDate;
    }
}
