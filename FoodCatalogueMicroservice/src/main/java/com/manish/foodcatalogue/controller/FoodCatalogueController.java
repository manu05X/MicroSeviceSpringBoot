package com.manish.foodcatalogue.controller;

import com.manish.foodcatalogue.dto.FoodItemDTO;
import com.manish.foodcatalogue.service.FoodCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodCatalogue")
@CrossOrigin
public class FoodCatalogueController {
    private FoodCatalogueService foodCatalogueService;

    @Autowired
    public FoodCatalogueController(FoodCatalogueService foodCatalogueService) {
        this.foodCatalogueService = foodCatalogueService;
    }

//    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
//        FoodItemDTO savedFoodItem = foodCatalogueService.addFoodItem(foodItemDTO);
//        return savedFoodItem;
//    }

    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        FoodItemDTO savedFoodItem = foodCatalogueService.addFoodItem(foodItemDTO);
        return new ResponseEntity<>(savedFoodItem, HttpStatus.OK);
    }
}
