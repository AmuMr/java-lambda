package com.example.javalambda.entity;

import lombok.Data;

@Data
public class Fund {
    private String name;
    private double val;

    public Fund(String name) {
        this.name = name;
    }

    public Fund(String name, double val) {
        this.name = name;
        this.val = val;
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
