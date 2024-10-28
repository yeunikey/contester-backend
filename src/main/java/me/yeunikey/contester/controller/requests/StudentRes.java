package me.yeunikey.contester.controller.requests;

public class StudentRes {

    private String uniqueId;
    private String name;
    private String surname;

    public StudentRes(String uniqueId, String name, String surname) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.surname = surname;
    }
}
