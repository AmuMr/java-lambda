package com.example.javalambda.entity;

import lombok.Data;

@Data
public class BaseDto<T> {

    private String code;
    private String message;
    private T content;


}
