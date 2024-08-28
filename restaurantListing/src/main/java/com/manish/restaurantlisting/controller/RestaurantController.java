package com.manish.restaurantlisting.controller;

import com.manish.restaurantlisting.dto.RestaurantDTO;
import com.manish.restaurantlisting.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/fetchAllRestaurants")
    public ResponseEntity<List<RestaurantDTO>> fetchAllRestaurants(){
        List<RestaurantDTO> allRestaurants = restaurantService.findAllRestaurants();
        return new ResponseEntity<>(allRestaurants, HttpStatus.OK);
    }

    @PostMapping("/addRestaurant")
    public ResponseEntity<RestaurantDTO> saveRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO restaurantAdded = restaurantService.addRestaurantInDB(restaurantDTO);
        return new ResponseEntity<>(restaurantAdded, HttpStatus.CREATED);
    }

    @GetMapping("fetchById/{id}")
    public ResponseEntity<RestaurantDTO> findRestaurantById(@PathVariable Integer id) {
        return restaurantService.fetchRestaurantById(id);
    }

}


/*

@RestController
It will make sure that your response is always going to be in the Json format and it's not is going to map
to any JSP or some models.
So we're going to use it as rest controller because most of the time we're going to return as applications
as Json only.

@RequestMapping("/resturant")
Request mapping to annotate this controller with the base path or the URL that is restutant

@Autowire
Here in the controller you always auto wire the service layer.

Rest endpoints and post endpoints
1> get endpoints to fetch all restaurants together to show it on the listing page.
2> find by ID that rest endpoint will be used by food listing service when we need to pass a
proper restaurant ID for which the menu has to be fetched.
3> to save restaurants in the database that will be used internally for us to add restaurants
to the database.

*/