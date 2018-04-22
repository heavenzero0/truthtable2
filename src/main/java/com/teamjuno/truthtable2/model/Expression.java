package com.teamjuno.truthtable2.model;

import java.util.ArrayList;

public class Expression {
    private String variable;
    private ArrayList<String> value = new ArrayList<>();

    public Expression() {
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public ArrayList<String> getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value.add(value);
    }
}
