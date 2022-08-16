package com.eatco.compensationservice.service;

import java.util.List;

import com.eatco.compensationservice.dto.CategoryDto;
import com.eatco.compensationservice.exception.CustomValidationException;
import com.eatco.compensationservice.model.Category;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto category);

    List<CategoryDto> fetchAllCategories();

    CategoryDto updateCategory(CategoryDto categoryDto) throws CustomValidationException;

    void deleteCategory(Long categoryId);

    Category getCategory(Long categoryId) throws CustomValidationException;
}
