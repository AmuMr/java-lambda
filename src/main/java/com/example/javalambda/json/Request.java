package com.example.javalambda.json;

import lombok.Data;

@Data
public class Request<T> {

    private String applicationNo;

    private T data;


}
