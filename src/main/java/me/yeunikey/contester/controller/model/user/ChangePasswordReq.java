package me.yeunikey.contester.controller.model.user;

public class ChangePasswordReq {

    private String newPassword;

    public ChangePasswordReq(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
