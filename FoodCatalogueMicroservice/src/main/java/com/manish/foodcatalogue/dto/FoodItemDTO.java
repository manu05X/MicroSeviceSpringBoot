package com.manish.foodcatalogue.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FoodItemDTO {
    private int id;
    private String itemName;
    private String itemDescription;
    private boolean isVeg;
    private Long price;
    private Integer restaurantId;
    private Integer quantity;
}
