package com.example.javalambda;

import cn.hutool.core.date.DateUtil;
import com.example.javalambda.callback.ParseService;
import com.example.javalambda.config.TimeDemo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
class JavaLambdaApplicationTests {

    @Autowired
    private ParseService parseService;


    @Test
    public void test1() {
        parseService.parse("C");
    }


    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test43() {
        String set = stringRedisTemplate.opsForValue().getAndSet("A", "1");
        System.out.println(set);

    }


    @Autowired
    private TimeDemo timeDemo;

    @Test
    public void test44() throws Exception {
        for (int i = 0; i < 100; i++) {
            System.out.println(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));

        }
    }


}
