package com.eatco.compensationservice.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalCompensationDTO {
	private BigDecimal totalCompensation;
	private BigDecimal totalCo2Compensated;

}
