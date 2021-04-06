package com.example.javalambda.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {


    @RequestMapping("/hi")
    public String sayHello(){

        return "hello word";
    }


}
