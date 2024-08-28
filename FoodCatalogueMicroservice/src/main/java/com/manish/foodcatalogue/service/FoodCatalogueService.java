package com.manish.foodcatalogue.service;

import com.manish.foodcatalogue.dto.FoodItemDTO;
import com.manish.foodcatalogue.entity.FoodItem;
import com.manish.foodcatalogue.mapper.FoodItemMapper;
import com.manish.foodcatalogue.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodCatalogueService {
    private FoodItemRepo foodItemRepo;
    private FoodItemMapper foodItemMapper;

    @Autowired
    public FoodCatalogueService(FoodItemRepo foodItemRepo, FoodItemMapper foodItemMapper) {
        this.foodItemRepo = foodItemRepo;
        this.foodItemMapper = foodItemMapper;
    }

    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
        FoodItem foodItem = foodItemMapper.mapFoodItemDTOToFoodItem(foodItemDTO);
        FoodItem foodItemSavedInDB = foodItemRepo.save(foodItem);
        return foodItemMapper.mapFoodItemToFoodItemDTO(foodItemSavedInDB);
    }
}
