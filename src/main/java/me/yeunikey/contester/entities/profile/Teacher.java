package me.yeunikey.contester.entities.profile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import me.yeunikey.contester.entities.Profile;
import me.yeunikey.contester.entities.User;

@Entity
@Table(name = "teachers")
public class Teacher extends Profile {

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;

    public Teacher() {
    }

    public Teacher(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
