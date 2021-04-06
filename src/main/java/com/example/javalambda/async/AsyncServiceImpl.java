package com.example.javalambda.async;


import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncServiceImpl implements AsyncService {


    @Async
    public void asyncTask() {
        long startTime = System.currentTimeMillis();
        try {
            //模拟耗时
            for (int i = 0; i < 1000; i++) {
                System.out.println(i);
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "：void asyncTask()，耗时：" + (endTime - startTime));
    }

}