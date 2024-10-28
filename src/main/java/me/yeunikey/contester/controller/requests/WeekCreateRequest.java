package me.yeunikey.contester.controller.requests;

import java.time.LocalDateTime;

public class WeekCreateRequest {

    private String name;
    private boolean closed;
    private LocalDateTime deadlineDate;

    public WeekCreateRequest() {
    }

    public WeekCreateRequest(String name, boolean closed, LocalDateTime deadlineDate) {
        this.name = name;
        this.closed = closed;
        this.deadlineDate = deadlineDate;
    }

    public String getName() {
        return name;
    }

    public boolean isClosed() {
        return closed;
    }

    public LocalDateTime getDeadlineDate() {
        return deadlineDate;
    }
}
