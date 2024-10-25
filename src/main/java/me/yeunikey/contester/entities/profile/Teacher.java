package me.yeunikey.contester.entities.profile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import me.yeunikey.contester.entities.Profile;
import me.yeunikey.contester.entities.User;

@Entity
@Table(name = "teachers")
public class Teacher extends Profile {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;

    public Teacher() {
    }

    public Teacher(User user, String name, String surname) {
        this.user = user;
        this.name = name;
        this.surname = surname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
