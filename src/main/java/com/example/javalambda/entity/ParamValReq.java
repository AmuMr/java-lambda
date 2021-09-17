package com.example.javalambda.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ParamValReq implements Serializable {

    @NotNull(message = "请求流水号不能为空")
    private String reqNo;

    @NotNull(message = "姓名不能为空")
    private String name;

    @NotNull(message = "年龄不能为空")
    private String age;


}
