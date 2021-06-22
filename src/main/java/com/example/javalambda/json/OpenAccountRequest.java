package com.example.javalambda.json;

import lombok.Data;

import java.util.Date;

@Data
public class OpenAccountRequest {

    private String name;
    private int age;
    private boolean sex;
    private Date birthday;
}
