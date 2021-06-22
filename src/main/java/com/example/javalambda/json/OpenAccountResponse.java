package com.example.javalambda.json;

import lombok.Data;

import java.util.Date;

@Data
public class OpenAccountResponse extends Request {

    private int openStatus;
    private String name;
    private Date openTime;

}
