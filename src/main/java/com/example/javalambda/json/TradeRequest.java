package com.example.javalambda.json;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TradeRequest {

    private String accountNo;

    private BigDecimal amount;


}
