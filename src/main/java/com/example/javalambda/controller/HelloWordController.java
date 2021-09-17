package com.example.javalambda.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HelloWordController {


    @RequestMapping("/hi")
    public LocalDateTime sayHello() {

        return LocalDateTime.now();
    }


}
