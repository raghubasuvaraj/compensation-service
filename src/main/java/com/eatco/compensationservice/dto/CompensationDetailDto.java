package com.eatco.compensationservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CompensationDetailDto {
    private Long categoryId;
    private String itemNbr;
    private int quantity;
    private BigDecimal amount;
    private BigDecimal co2Amount;
}
