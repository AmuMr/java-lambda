package com.example.javalambda.controller;

import com.example.javalambda.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HelloWordController {


    @RequestMapping(value = "/hi", method = RequestMethod.POST)
    public LocalDateTime sayHello(@RequestBody User user) {

        return LocalDateTime.now();
    }


}
