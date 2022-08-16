package com.eatco.compensationservice.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eatco.compensationservice.dto.CompensationDashboardDto;
import com.eatco.compensationservice.dto.CompensationDetailDto;
import com.eatco.compensationservice.dto.CompensationDto;
import com.eatco.compensationservice.dto.TotalCompensationDTO;
import com.eatco.compensationservice.exception.CustomValidationException;
import com.eatco.compensationservice.service.CompensationService;
import com.eatco.compensationservice.util.PaginationResult;


@RestController
@RequestMapping("/compensation")
public class CompensationController {

    @Autowired
    private CompensationService compensationService;

//    @PostMapping(value = "add")
//    public ResponseEntity<?> addCompensation(@RequestBody List<CompensationDetailDto> compensationDto) {
//        compensationService.addCompensation(compensationDto);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @GetMapping(value = "list/get")
    public ResponseEntity<PaginationResult<CompensationDashboardDto>> fetchCompensationList(
            @RequestParam(name = "pageNo", defaultValue = "1", required = false) Integer pageNo,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "fromDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestParam(name = "toDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate,
            @RequestParam(name = "userId") String userId) {
        return new ResponseEntity<>(compensationService.fetchFilteredResults(pageNo, size, fromDate, toDate, userId), HttpStatus.OK);
    }

    @GetMapping(value = "get/{compensationId}")
    public ResponseEntity<CompensationDto> fetchByCompensationId(@RequestParam(name = "compensationId") String compensationId) throws CustomValidationException {
        return new ResponseEntity<>(compensationService.fetchByCompensationId(compensationId), HttpStatus.OK);
    }

    @PutMapping(value = "update/{cmp_id}")
    public ResponseEntity<CompensationDto> updateCompensationDetails(@RequestBody CompensationDetailDto compensationDetailDto, @RequestParam(name = "cmp_id") String compensationId) throws CustomValidationException {
        return new ResponseEntity<>(compensationService.updateCompensationDetails(compensationDetailDto, compensationId), HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{compensationId}")
    public ResponseEntity<?> deleteCompensation(@RequestParam(name = "compensationId") String compensationId) throws CustomValidationException {
        compensationService.deleteCompensation(compensationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "get/total/{userId}")
    public ResponseEntity<TotalCompensationDTO> fetchTotalCompensationAmount(@PathVariable String userId) throws CustomValidationException {
        return new ResponseEntity<>(compensationService.fetchTotalCompensationAmount(userId), HttpStatus.OK);
    }
}
