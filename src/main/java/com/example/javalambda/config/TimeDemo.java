package com.example.javalambda.config;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Scope("prototype")
public class TimeDemo {

    public Date getTime() {

        return new Date();
    }
}
