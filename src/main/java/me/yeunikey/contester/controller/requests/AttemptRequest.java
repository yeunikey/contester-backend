package me.yeunikey.contester.controller.requests;

public class AttemptRequest {

    private String problemId;
    private String language;
    private String[] code;

    public AttemptRequest(String problemId, String language, String[] code) {
        this.problemId = problemId;
        this.language = language;
        this.code = code;
    }

    public String getProblemId() {
        return problemId;
    }

    public String getLanguage() {
        return language;
    }

    public String[] getCode() {
        return code;
    }
}
