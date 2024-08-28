package com.manish.restaurantlisting.service;

import com.manish.restaurantlisting.dto.RestaurantDTO;
import com.manish.restaurantlisting.entity.Restaurant;
import com.manish.restaurantlisting.mapper.RestaurantMapper;
import com.manish.restaurantlisting.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService1 {

    @Autowired
    RestaurantRepo restaurantRepo;
    //private final RestaurantMapper restaurantMapper = RestaurantMapper.INSTANCE;


    /*
    public List<RestaurantDTO> findAllRestaurants(){
        //But since you're going to return a DTO and what we're going to get here is a restaurant entity.
        //You cannot return directly like this.
        //return restaurantRepo.findAll();

        //get all the list of restaurants entity and then map it to a list of DTO's as we directly can't
        // return entity to controller for security.
        List<Restaurant> restaurants = restaurantRepo.findAll();
        // So we'll be creating one more child package known as mapper who is going to have the mapping
        // One simple interface RestaurantMapper.

        List<RestaurantDTO> restaurantDTOList = restaurants.stream()
                .map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant))
                .collect(Collectors.toList());

        return restaurantDTOList;
    }
*/

    public List<RestaurantDTO> findAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepo.findAll();
        List<RestaurantDTO> restaurantDTOList = restaurants.stream().map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant)).collect(Collectors.toList());
        return restaurantDTOList;
    }

//    public List<RestaurantDTO> findAllRestaurants() {
//        List<Restaurant> restaurants = restaurantRepo.findAll();
//        return restaurants.stream()
//                .map(RestaurantMapper::mapRestaurantToRestaurantDTO)
//                .collect(Collectors.toList());
//    }

    public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {
        Restaurant savedRestaurant =  restaurantRepo.save(RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO));
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(savedRestaurant);
        //return null;
    }

//    public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {
//        // Use static method from RestaurantMapper to convert DTO to entity
//        Restaurant restaurant = RestaurantMapper.mapRestaurantDTOToRestaurant(restaurantDTO);
//        // Save the entity using repository
//        Restaurant savedRestaurant = restaurantRepo.save(restaurant);
//        // Convert the saved entity back to DTO
//        return RestaurantMapper.mapRestaurantToRestaurantDTO(savedRestaurant);
//    }

//    public Restaurant addRestaurantInDB(Restaurant restaurant) {
//        return null;
//    }

    public ResponseEntity<RestaurantDTO> fetchRestaurantById(Integer id) {
        Optional<Restaurant> restaurant =  restaurantRepo.findById(id);
        if(restaurant.isPresent()){
            return new ResponseEntity<>(RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

//    public ResponseEntity<RestaurantDTO> fetchRestaurantById(Integer id) {
//        Optional<Restaurant> restaurant = restaurantRepo.findById(id);
//        if (restaurant.isPresent()) {
//            // Convert the found entity to DTO and return
//            return new ResponseEntity<>(RestaurantMapper.mapRestaurantToRestaurantDTO(restaurant.get()), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//    }


}

/*
I'm going to open a stream Java eight stream[restaurants.stream()],

Now I want to map each of the restaurant entity that I get from the database to RestaurantDTO.
So I'm going to use map simple Java eight function map to convert each restaurant that I get from database to restaurantDTO.
And how do we do that? -> By using the mapper i.e RestaurantMapper
So we have restaurant mapper with us.Restaurant mapper has an instance[INSTANCE] that we have created and that has a capability to help us access a
method called as mapRestaurantToRestaurantDTO . Now, since you got a restaurant as an input, you can pass that restaurant as an input to
this method mapRestaurantToRestaurantDTO(restaurant) , and at the end collect them all to list.

[.map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant)).collect(Collectors.toList());]


And then when you collect it, the resultant list will be the list of RestaurantDTO now and not the restaurant entity
that we got from DB.


So here what we get is RestaurantDTO list, and this list is capable enough to be returned from findAllRestaurants to client.


So this is how just in the service layer you hit the repository, fetch all the restaurant entities.
But before going to the controller and interacting with the client, you have converted your entities
to DTO and hence prevent any of the client interactions to have any restaurant entity as a medium of communication.

Now we return this restaurant list i.e restaurantDTOList to Controller.

_________________________________________________________________________________
restaurantRepo.findAll();

database-platform: org.hibernate.dialect.MySQL5InnoDBDialect
MySQL5InnoDBDialect is going to be the one who is responsible to convert your ORM based language to my SQL based query.
In my service layer with repository, I said [restaurantRepo.findAll()] this is ORM language i.e English language.

How will it convert into select Star from restaurantDB? How will it do that?

SO using this hibernate dialect, which is a interface that converts your ORM language to database specific language.
This is important whenever you have a framework at one side and a data source like MySQL or Oracle on
another side, if you have used Oracle, this dialect would have changed because the querying pattern
for MySQL is different from the Oracle.
*/