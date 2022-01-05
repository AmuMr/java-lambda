package com.example.javalambda.converter;


import com.example.javalambda.entity.BaseDto;

import java.util.function.Function;

public interface ConverterHandel<I, O> extends Function<I, O> {


    default BaseDto convert(I input) {
        BaseDto dto = new BaseDto();
        O apply = this.apply(input);
        dto.setContent(apply);
        return dto;
    }

}
