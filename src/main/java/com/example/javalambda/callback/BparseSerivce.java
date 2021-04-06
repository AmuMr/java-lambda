package com.example.javalambda.callback;

import org.springframework.stereotype.Service;

@Service
public class BparseSerivce implements IParse,Callback{

    @Override
    public String type() {
        return "B";
    }

    @Override
    public void parse() {
        System.out.println(this.getClass().getSimpleName() + "解析成功");
    }

    @Override
    public void call(String result) {
        System.out.println(result);
    }
}
