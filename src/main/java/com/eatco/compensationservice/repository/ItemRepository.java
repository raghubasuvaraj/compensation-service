package com.eatco.compensationservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eatco.compensationservice.model.Item;

public interface ItemRepository extends JpaRepository<Item, String> {
    List<Item> findAllItemsByCategoryId(Long categoryId);
}
