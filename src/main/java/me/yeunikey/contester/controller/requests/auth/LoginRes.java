package me.yeunikey.contester.controller.requests.auth;

public class LoginRes {

    private String token;

    public LoginRes(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
