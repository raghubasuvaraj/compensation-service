package com.eatco.compensationservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CompensationDto {
    private String compensationId;
    private List<CompensationDetailDto> compensationDetails;
    private String currency;
    private BigDecimal totalAmount;
    private BigDecimal co2Amount;
}