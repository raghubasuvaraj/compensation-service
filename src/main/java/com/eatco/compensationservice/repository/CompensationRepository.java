package com.eatco.compensationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eatco.compensationservice.model.Compensation;

public interface CompensationRepository extends JpaRepository<Compensation, String>, CompensationCustomRepo {
}
