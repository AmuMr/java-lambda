package com.example.javalambda;

import com.example.javalambda.callback.ParseService;
import com.example.javalambda.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JavaLambdaApplicationTests {

    @Autowired
    private ParseService parseService;

    @Value("${https.url}")
    private String url;


    @Test
    public void test1() {
        parseService.parse("C");
    }

    @Autowired
    private User user;

    @Test
    public void test2() {
        System.out.println(user.getName());
    }





}
