package com.eatco.compensationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eatco.compensationservice.dto.CategoryDto;
import com.eatco.compensationservice.exception.CustomValidationException;
import com.eatco.compensationservice.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping(value = "create")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto category) {
		return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.OK);
	}

	@GetMapping(value = "list/get")
	public ResponseEntity<List<CategoryDto>> getCategories() {
		return new ResponseEntity<>(categoryService.fetchAllCategories(), HttpStatus.OK);
	}

	@PutMapping(value = "update")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) throws CustomValidationException {
		return new ResponseEntity<>(categoryService.updateCategory(categoryDto), HttpStatus.OK);
	}

	@PutMapping(value = "delete/{categoryId}")
	public ResponseEntity<?> fetchFirmDetails(@RequestParam(name = "categoryId") Long categoryId) {
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
