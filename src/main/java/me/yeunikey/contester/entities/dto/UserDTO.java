package me.yeunikey.contester.entities.dto;

import me.yeunikey.contester.entities.AccountType;

public class UserDTO {

    private String uniqueId;
    private AccountType type;
    private ProfileDTO profile;

    public UserDTO(String uniqueId, AccountType type, ProfileDTO profile) {
        this.uniqueId = uniqueId;
        this.type = type;
        this.profile = profile;
    }

}
