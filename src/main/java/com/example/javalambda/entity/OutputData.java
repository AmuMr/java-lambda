package com.example.javalambda.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: zhuoli
 * @Date: 2018/8/9 11:01
 * @Description:
 */
@Data
@AllArgsConstructor
public class OutputData {
    private int id;
    private String name;
    private String type;
    private int amount;

}