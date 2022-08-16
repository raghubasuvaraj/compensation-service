package com.eatco.compensationservice.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eatco.compensationservice.dto.CompensationDashboardDto;
import com.eatco.compensationservice.dto.CompensationDetailDto;
import com.eatco.compensationservice.dto.CompensationDto;
import com.eatco.compensationservice.dto.TotalCompensationDTO;
import com.eatco.compensationservice.exception.CustomValidationException;
import com.eatco.compensationservice.exception.ErrorCode;
import com.eatco.compensationservice.model.Cmp_detail_id;
import com.eatco.compensationservice.model.Compensation;
import com.eatco.compensationservice.model.CompensationDetail;
import com.eatco.compensationservice.repository.CompensationDetailRepo;
import com.eatco.compensationservice.repository.CompensationRepository;
import com.eatco.compensationservice.service.CompensationService;
import com.eatco.compensationservice.util.PaginationResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CompensationServiceImpl implements CompensationService {
    @Autowired
    CompensationRepository compensationRepo;

    @Autowired
    CompensationDetailRepo compensationDetailRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;
    
//    @Autowired
//    private PdfService pdfService;

//    @Override
//    public void addCompensation(List<CompensationDetailDto>  compensationDetailDto) {
//
//        Compensation compensation = new Compensation();
//        compensation.setTotalAmount(compensationDetailDto.stream().map(CompensationDetailDto::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add));
//        compensation.setCo2TotalAmount(compensationDetailDto.stream().map(CompensationDetailDto::getCo2Amount).reduce(BigDecimal.ZERO, BigDecimal::add));
//        compensation = compensationRepo.save(compensation);
//        List<CompensationDetail> compensationDetails = new ArrayList<>();
//        for (CompensationDetailDto detailDto:compensationDetailDto) {
//            compensationDetails.add(CompensationDetail.builder()
//                    .amount(detailDto.getAmount())
//                    .co2Amount(detailDto.getCo2Amount())
//                    .quantity(detailDto.getQuantity())
//                    .cmp_detail_id(new Cmp_detail_id(detailDto.getItemNbr(),detailDto.getCategoryId(),compensation.getCmpId()))
//                    .compensation(compensation)
//                    .build());
//        }
//        compensationDetailRepo.saveAll(compensationDetails);
//        compensation.setCompensationDetails(compensationDetails);
//        pdfService.generateInvoice(compensation);
//    }

    @Override
    public PaginationResult<CompensationDashboardDto> fetchFilteredResults(Integer pageNo, Integer size, Date fromDate, Date toDate,String userId) {
        PageRequest pageRequest =  PageRequest.of(pageNo-1, size);
        Page<Compensation> result = compensationRepo.getCompensationByDate(fromDate, toDate, userId,pageRequest);
        return convertDataToPaginationResult(result, pageRequest);
    }

    private PaginationResult<CompensationDashboardDto> convertDataToPaginationResult(
            Page<Compensation> paginatedResult, Pageable pageRequest) {
        PaginationResult<CompensationDashboardDto> paginationResult = new PaginationResult<>();
        paginationResult.setContent(formContent(paginatedResult));
        paginationResult.setPageNumber(paginatedResult.getNumber()+1);
        paginationResult.setSize(pageRequest.getPageSize());
        paginationResult.setTotalPages(paginatedResult.getTotalPages());
        paginationResult.setTotalCount(paginatedResult.getTotalElements());
        return paginationResult;
    }

    private List<CompensationDashboardDto> formContent(Page<Compensation> paginatedResult) {
        List<CompensationDashboardDto> compensationDtoList = modelMapper.map(paginatedResult.getContent(),
                new TypeToken<List<CompensationDashboardDto>>() {
                }.getType());
        return compensationDtoList;
    }

    @Override
    public CompensationDto updateCompensationDetails(CompensationDetailDto compensationDto, String compensationId) throws CustomValidationException {
        Compensation comp = compensationRepo.findById(compensationId).orElseThrow( () -> new CustomValidationException(ErrorCode.EATCO2_MANAGEMENT_1009));
        CompensationDetail compensationDetails = getCompensationDetail(compensationDto, compensationId);
        boolean exists = false;
        for(CompensationDetail cmpDetail: comp.getCompensationDetails()){
            if (cmpDetail.getCmp_detail_id().equals(compensationDetails.getCmp_detail_id())){
                exists=true;
                comp.setTotalAmount(comp.getTotalAmount().subtract(cmpDetail.getAmount()).add(compensationDetails.getAmount()));
                comp.setCo2TotalAmount(comp.getCo2TotalAmount().subtract(cmpDetail.getCo2Amount()).add(compensationDetails.getCo2Amount()));
                break;
            }
        }
        if (!exists) {
            throw new CustomValidationException(ErrorCode.EATCO2_MANAGEMENT_1008);
        }

        //compensationDetailRepo.save(compensationDetails);
        comp = compensationRepo.save(comp);
        return prepareCompensationDtoResp(comp);
    }

    private CompensationDto prepareCompensationDtoResp(Compensation comp) {
        CompensationDto resp = new CompensationDto();
        resp.setCompensationId(comp.getCmpId());
        resp.setTotalAmount(comp.getTotalAmount());
        resp.setCompensationDetails(prepareCompensationDetailResp(comp.getCompensationDetails()));
        return resp;
    }

    private CompensationDetail getCompensationDetail(CompensationDetailDto compensationDto, String compensationId) {
        return CompensationDetail.builder()
                .amount(compensationDto.getAmount())
                .quantity(compensationDto.getQuantity())
                .cmp_detail_id(new Cmp_detail_id(compensationDto.getItemNbr(), compensationDto.getCategoryId(), compensationId))
                .build();
    }

    @Override
    public void deleteCompensation(String compensationId) throws CustomValidationException {
        compensationRepo.findById(compensationId).orElseThrow( () -> new CustomValidationException(ErrorCode.EATCO2_MANAGEMENT_1009));
        compensationRepo.deleteById(compensationId);
    }

    @Override
    public CompensationDto fetchByCompensationId(String compensationId) throws CustomValidationException {
        Compensation compensationDetails = compensationRepo.findById(compensationId).orElseThrow( () -> new CustomValidationException(ErrorCode.EATCO2_MANAGEMENT_1009));
        CompensationDto response = new CompensationDto();
        response.setTotalAmount(compensationDetails.getTotalAmount());
        response.setCompensationId(compensationId);
        response.setCompensationDetails(prepareCompensationDetailResp(compensationDetails.getCompensationDetails()));
        return response;
    }

    private List<CompensationDetailDto> prepareCompensationDetailResp(List<CompensationDetail> compensationDetails) {
        List<CompensationDetailDto> resp = new ArrayList<>();
        for (CompensationDetail cd:compensationDetails) {
            CompensationDetailDto cdto = new CompensationDetailDto();
            cdto.setAmount(cd.getAmount());
            cdto.setCategoryId(cd.getCmp_detail_id().getCategoryId());
            cdto.setQuantity(cd.getQuantity());
            cdto.setItemNbr(cd.getCmp_detail_id().getItemNbr());
            resp.add(cdto);
        }
        return resp;
    }

	@Override
	public TotalCompensationDTO fetchTotalCompensationAmount(String userId) throws CustomValidationException {
		
		TotalCompensationDTO result = new TotalCompensationDTO();
		
		List<CompensationDetail> queryResult=  compensationDetailRepo.findAllByCreatedBy(userId);
		if(queryResult.size()>0) {
			result.setTotalCompensation(queryResult.stream().map(x -> x.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add));
			result.setTotalCo2Compensated(queryResult.stream().map(x -> x.getCo2Amount()).reduce(BigDecimal.ZERO, BigDecimal::add));
		}
		return result;
	}
}
