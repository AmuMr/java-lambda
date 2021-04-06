package com.example.javalambda.callback;

import org.springframework.stereotype.Service;

@Service
public class CparseSerivce implements IParse,Callback{

    @Override
    public String type() {
        return "C";
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
