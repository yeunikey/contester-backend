package me.yeunikey.contester.entities.assignments;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "tests")
public class Test {

    @Id
    private String uniqueId = UUID.randomUUID().toString();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "test_inputs", joinColumns = @JoinColumn(name = "test_id"))
    @Column(name = "input")
    private List<String> input;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "test_outputs", joinColumns = @JoinColumn(name = "test_id"))
    @Column(name = "output")
    private List<String> output;

    @Column(name = "hidden")
    private boolean hidden;

    public Test() {
    }

    public Test(List<String> input, List<String> output, boolean hidden) {
        this.input = input;
        this.output = output;
        this.hidden = hidden;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public List<String> getInput() {
        return input;
    }

    public void setInput(List<String> input) {
        this.input = input;
    }

    public List<String> getOutput() {
        return output;
    }

    public void setOutput(List<String> output) {
        this.output = output;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
