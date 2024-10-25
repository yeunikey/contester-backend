package me.yeunikey.contester.entities.assignments;

import java.util.List;

public class Test {

    private List<String> input;
    private List<String> output;

    public Test() {
    }

    public Test(List<String> input, List<String> output) {
        this.input = input;
        this.output = output;
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
}
