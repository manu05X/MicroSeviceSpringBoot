package com.manish.foodcatalogue.dto;

import com.manish.foodcatalogue.entity.FoodItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodCataloguePage {

    private List<FoodItem> foodItemsList;
    private Restaurant restaurant;
}

/*
So in the catalog page we also show them this restaurant details and foodItemsList.

So that is why we'll be needing two things the list of food items, which is entity for us here, and
the restaurant details, which is a DTO here.

So together they form the whole catalog page.
When you click on a particular restaurant, you will see all these two details on your page.

So that will be coming from this particular detail data transfer object.
* */