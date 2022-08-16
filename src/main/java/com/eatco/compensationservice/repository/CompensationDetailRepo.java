package com.eatco.compensationservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eatco.compensationservice.model.Cmp_detail_id;
import com.eatco.compensationservice.model.CompensationDetail;

public interface CompensationDetailRepo extends JpaRepository<CompensationDetail, Cmp_detail_id> {
	
	public List<CompensationDetail> findAllByCreatedBy(String createdBy);
}
