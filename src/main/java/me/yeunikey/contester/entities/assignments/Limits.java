package me.yeunikey.contester.entities.assignments;

public class Limits {

    private long timeLimit;
    private long memoryLimit;

    public Limits() {
    }

    public Limits(long timeLimit, long memoryLimit) {
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
    }

    public long getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(long timeLimit) {
        this.timeLimit = timeLimit;
    }

    public long getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(long memoryLimit) {
        this.memoryLimit = memoryLimit;
    }
}
