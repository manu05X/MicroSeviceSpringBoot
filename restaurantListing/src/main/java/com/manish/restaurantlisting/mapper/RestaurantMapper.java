package com.manish.restaurantlisting.mapper;

import com.manish.restaurantlisting.dto.RestaurantDTO;
import com.manish.restaurantlisting.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    Restaurant mapRestaurantDTOToRestaurant(RestaurantDTO restaurantDTO);

    RestaurantDTO mapRestaurantToRestaurantDTO(Restaurant restaurant);

}

/*
Which will be mapped with the rate mapper at the rate mapper will be imported from map strut.

We'll be needing two methods:
1>one to map Restaurant entity to RestaurantDTO (mapRestaurantToRestaurantDTO)
2> one to map RestaurantDTO to Restaurant entity.(mapRestaurantDTOToRestaurant)

To use these methods in your service layer, you need to create instance of this RestaurantMapper class only.

And using this instance, you're going to call all the methods which we're going to write in this interface,
which is abstract method (mapRestaurantDTOToRestaurant and mapRestaurantToRestaurantDTO).
Implementation of this interface will be created automatically by Spring Boot for you, which will be
having all the setters and getters and mapping between your DTO to entity and entity to DTO.

*/
