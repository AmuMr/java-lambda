package com.example.javalambda.callback;

import org.springframework.stereotype.Service;

@Service
public class AparseSerivce implements IParse{

    @Override
    public String type() {
        return "A";
    }

    @Override
    public void parse() {
        System.out.println(this.getClass().getSimpleName() + "解析成功");
    }
}
