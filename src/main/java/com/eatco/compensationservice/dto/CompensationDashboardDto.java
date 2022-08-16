package com.eatco.compensationservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class CompensationDashboardDto {
    private String compensationId;
    private String currency;
    private BigDecimal totalAmount;
    private BigDecimal co2Amount;
    private Date createdAt;
}
