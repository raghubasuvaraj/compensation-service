package com.eatco.compensationservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eatco.compensationservice.dto.CategoryDto;
import com.eatco.compensationservice.exception.CustomValidationException;
import com.eatco.compensationservice.exception.ErrorCode;
import com.eatco.compensationservice.model.Category;
import com.eatco.compensationservice.repository.CategoryRepository;
import com.eatco.compensationservice.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    ObjectMapper objectMapper;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.save(objectMapper.convertValue(categoryDto, Category.class));
        return objectMapper.convertValue(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> fetchAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDto> result = new ArrayList<>();
        categoryList.forEach(x -> result.add(objectMapper.convertValue(x , CategoryDto.class)));
        return result;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) throws CustomValidationException {
        categoryRepository.findById(categoryDto.getCategoryId()).orElseThrow( () -> new CustomValidationException(ErrorCode.EATCO2_MANAGEMENT_1008));
        Category category = categoryRepository.save(objectMapper.convertValue(categoryDto, Category.class));
        return objectMapper.convertValue(category, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public Category getCategory(Long categoryId) throws CustomValidationException {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new CustomValidationException(ErrorCode.EATCO2_MANAGEMENT_1008));
    }
}
