package com.manish.foodcatalogue.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String itemName;
    private String itemDescription;
    private boolean isVeg;
    private Long price;
    private Integer restaurantId;
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer quantity;
}
