package com.example.javalambda.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: zhuoli
 * @Date: 2018/8/9 10:22
 * @Description:
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class AClass {
    private Integer id;
    private String name;
    private String description;
}