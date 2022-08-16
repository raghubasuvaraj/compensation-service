package com.eatco.compensationservice.service;

import java.util.Date;
import java.util.List;

import com.eatco.compensationservice.dto.CompensationDashboardDto;
import com.eatco.compensationservice.dto.CompensationDetailDto;
import com.eatco.compensationservice.dto.CompensationDto;
import com.eatco.compensationservice.dto.TotalCompensationDTO;
import com.eatco.compensationservice.exception.CustomValidationException;
import com.eatco.compensationservice.util.PaginationResult;

public interface CompensationService {
  //  void addCompensation(List<CompensationDetailDto>  compensationDto);

    PaginationResult<CompensationDashboardDto> fetchFilteredResults(Integer pageNo, Integer size, Date fromDate, Date toDate, String userId);

    CompensationDto updateCompensationDetails(CompensationDetailDto compensationDto, String compensationId) throws CustomValidationException;

    void deleteCompensation(String compensationId) throws CustomValidationException;

    CompensationDto fetchByCompensationId(String compensationId) throws CustomValidationException;
    
    TotalCompensationDTO fetchTotalCompensationAmount(String userId) throws CustomValidationException;
}
