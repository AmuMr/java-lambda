package com.example.javalambda;

import com.example.javalambda.callback.ParseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JavaLambdaApplicationTests {

    @Autowired
    private ParseService parseService;



    @Test
    public void test28() {
        parseService.parse("C");
    }



}
