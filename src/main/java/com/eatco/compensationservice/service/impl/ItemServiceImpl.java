package com.eatco.compensationservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eatco.compensationservice.dto.ItemDto;
import com.eatco.compensationservice.exception.CustomValidationException;
import com.eatco.compensationservice.exception.ErrorCode;
import com.eatco.compensationservice.model.Category;
import com.eatco.compensationservice.model.Item;
import com.eatco.compensationservice.repository.ItemRepository;
import com.eatco.compensationservice.service.CategoryService;
import com.eatco.compensationservice.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepo;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public ItemDto addItemDetails(ItemDto itemDto) throws CustomValidationException {
        Category category = categoryService.getCategory(itemDto.getCategoryId());
        Item item = itemRepo.save(objectMapper.convertValue(itemDto, Item.class));
        return objectMapper.convertValue(item, ItemDto.class);
    }

    @Override
    public List<ItemDto> fetchItems(Long categoryId) throws CustomValidationException {
        categoryService.getCategory(categoryId);
        List<Item> items = itemRepo.findAllItemsByCategoryId(categoryId);
        List<ItemDto> resp = items.stream().map(x -> objectMapper.convertValue(x , ItemDto.class)).collect(Collectors.toList());
        return resp;
    }

    @Override
    public Item fetchItemById(String item_nbr) throws CustomValidationException {
        return itemRepo.findById(item_nbr).orElseThrow(() -> new CustomValidationException(ErrorCode.EATCO2_MANAGEMENT_1010));
    }

    @Override
    public ItemDto updateItem(ItemDto itemDto) throws CustomValidationException {
        itemRepo.findById(itemDto.getItemNbr()).orElseThrow( () -> new CustomValidationException(ErrorCode.EATCO2_MANAGEMENT_1010));
        Item item = itemRepo.save(objectMapper.convertValue(itemDto, Item.class));
        return objectMapper.convertValue(item, ItemDto.class);
    }

    @Override
    public void deleteItem(String itemNbr) throws CustomValidationException {
        itemRepo.findById(itemNbr).orElseThrow( () -> new CustomValidationException(ErrorCode.EATCO2_MANAGEMENT_1010));
        itemRepo.deleteById(itemNbr);
    }
}
