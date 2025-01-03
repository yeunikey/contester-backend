package me.yeunikey.contester.entities.profile;

import jakarta.persistence.*;
import me.yeunikey.contester.entities.Group;
import me.yeunikey.contester.entities.Profile;
import me.yeunikey.contester.entities.User;

@Entity
@Table(name = "students")
public class Student extends Profile {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;

    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacherId")
    private Teacher teacher;

    public Student() {
    }

    public Student(String name, String surname, Group group, Teacher teacher) {
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.teacher = teacher;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

}
