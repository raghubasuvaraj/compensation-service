package com.eatco.compensationservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemDto {
    private String itemNbr;
    private String itemName;
    private Long categoryId;
    private BigDecimal amount;
    private BigDecimal co2Amount;
}
