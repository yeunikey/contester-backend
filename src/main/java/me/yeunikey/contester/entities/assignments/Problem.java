package me.yeunikey.contester.entities.assignments;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Problem {

    @Id
    private String uniqueId = UUID.randomUUID().toString();

    @Column(name = "title")
    private String title;

    @Embedded
    private Limits limits;

    @Column(name = "lore")
    private String lore;

    @Embedded
    private List<Test> tests;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "correctCode")
    private Code correctCode;

    public Problem() {
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Limits getLimits() {
        return limits;
    }

    public void setLimits(Limits limits) {
        this.limits = limits;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public Code getCorrectCode() {
        return correctCode;
    }

    public void setCorrectCode(Code correctCode) {
        this.correctCode = correctCode;
    }
}
