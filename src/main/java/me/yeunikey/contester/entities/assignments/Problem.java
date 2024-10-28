package me.yeunikey.contester.entities.assignments;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "problems")
public class Problem {

    @Id
    private String uniqueId = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "weekId")
    private Week weekId;

    @Column(name = "title")
    private String title;

    @Embedded
    private Limits limits;

    @Column(name = "lore")
    private String lore;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "problem_id")
    private List<Test> tests;

    public Problem() {
    }

    public Problem(Week weekId, String title, Limits limits, String lore, List<Test> tests) {
        this.weekId = weekId;
        this.title = title;
        this.limits = limits;
        this.lore = lore;
        this.tests = tests;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Week getWeekId() {
        return weekId;
    }

    public void setWeekId(Week weekId) {
        this.weekId = weekId;
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

}
