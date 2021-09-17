package com.example.javalambda.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ParamValResp implements Serializable {

    private String respNo;

    public ParamValResp(String respNo) {
        this.respNo = respNo;
    }
}
