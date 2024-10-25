package me.yeunikey.contester.entities.dto.profile;

import me.yeunikey.contester.entities.dto.ProfileDTO;

public class StudentDTO extends ProfileDTO {

    private String name;
    private String surname;
    private String group;
    private String teacher;

    public StudentDTO(String uniqueId, String name, String surname, String group, String teacher) {
        super(uniqueId);
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.teacher = teacher;
    }
}
