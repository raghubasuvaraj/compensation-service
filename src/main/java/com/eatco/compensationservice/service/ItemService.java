package com.eatco.compensationservice.service;

import java.util.List;

import com.eatco.compensationservice.dto.ItemDto;
import com.eatco.compensationservice.exception.CustomValidationException;
import com.eatco.compensationservice.model.Item;

public interface ItemService {
    ItemDto addItemDetails(ItemDto itemDto) throws CustomValidationException;

    List<ItemDto> fetchItems(Long categoryId) throws CustomValidationException;

    Item fetchItemById(String item_nbr) throws CustomValidationException;

    ItemDto updateItem(ItemDto categoryDto) throws CustomValidationException;

    void deleteItem(String itemNbr) throws CustomValidationException;
}
