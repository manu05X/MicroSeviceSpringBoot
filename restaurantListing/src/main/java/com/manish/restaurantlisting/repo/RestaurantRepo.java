package com.manish.restaurantlisting.repo;

import com.manish.restaurantlisting.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // this create a bean for me that can be auto wired in our service
public interface RestaurantRepo extends JpaRepository<Restaurant, Integer> {
}
