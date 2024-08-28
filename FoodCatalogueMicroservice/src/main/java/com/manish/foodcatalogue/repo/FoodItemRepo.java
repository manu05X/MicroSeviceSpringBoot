package com.manish.foodcatalogue.repo;

import com.manish.foodcatalogue.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepo extends JpaRepository<FoodItem, Integer> {
}
