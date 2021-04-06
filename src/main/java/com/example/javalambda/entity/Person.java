package com.example.javalambda.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {

    private String id;
    private String name;
    private String code;
    private String val;

    public Person() {
    }

    public Person(String name, int val) {

        this.name = name;
        this.val = String.valueOf(val);
    }

}
