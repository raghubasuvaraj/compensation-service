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

import com.eatco.compensationservice.dto.ItemDto;
import com.eatco.compensationservice.exception.CustomValidationException;
import com.eatco.compensationservice.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@PostMapping(value = "add")
	public ResponseEntity<ItemDto> addItemDetails(@RequestBody ItemDto itemDto) throws CustomValidationException {
		return new ResponseEntity<>(itemService.addItemDetails(itemDto), HttpStatus.OK);
	}

	@GetMapping(value = "list/get")
	public ResponseEntity<List<ItemDto>> getItems(@RequestParam(name = "categoryId") Long categoryId) throws CustomValidationException {
		return new ResponseEntity<>(itemService.fetchItems(categoryId), HttpStatus.OK);
	}

	@PutMapping(value = "update")
	public ResponseEntity<ItemDto> updateItemDetails(@RequestBody ItemDto itemDto) throws CustomValidationException {
		return new ResponseEntity<>(itemService.updateItem(itemDto), HttpStatus.OK);
	}

	@PutMapping(value = "delete/{itemNbr}")
	public ResponseEntity<?> removeItem(@RequestParam(name = "itemNbr") String itemNbr) throws CustomValidationException {
		itemService.deleteItem(itemNbr);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
