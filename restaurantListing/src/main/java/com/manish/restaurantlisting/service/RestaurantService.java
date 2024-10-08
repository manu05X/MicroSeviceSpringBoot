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
public class RestaurantService {

    @Autowired
    RestaurantRepo restaurantRepo;

    /*
    public List<RestaurantDTO> findAllRestaurants() {
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



    public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {
        Restaurant savedRestaurant =  restaurantRepo.save(RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO));
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(savedRestaurant);
    }

    public ResponseEntity<RestaurantDTO> fetchRestaurantById(Integer id) {
        Optional<Restaurant> restaurant =  restaurantRepo.findById(id);
        if(restaurant.isPresent()){
            return new ResponseEntity<>(RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
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



_________________________

addRestaurantInDB

But what I can save is entity i.e Restaurant entity in DB. What I cannot save is RestaurantDTO.
So first I need to convert this RestaurantDTO to Restaurant entity.

How would I do that?
ANS -> RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO)
Request mapper dot instance dot mapRestaurantDTOToRestaurant and restaurantDTO is something we are already
getting from controller i.e front end(client).


Now we are using -> restaurantRepo.save()
 Restaurant savedRestaurant =  restaurantRepo.save(RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO));

When we run the save method [save()], the return type is what we are saving i.e actual restaurant entity,
so the return type will be restaurant entity saved -> [savedRestaurant]-> restaurant entity is the instance
which is returned when we save it in the repository.


Now we need to return what we have saved to database. So again, while returning we need to return the DTO and not the entity.
So we again we need to convert the savedRestaurant i.e restaurant entity to restaurantDTO using mapper mapRestaurantToRestaurantDTO
and returned it on the controller layer.



So we are getting DTO but we are saving the entity in our DB by converting it and the client could not know the
structure or internal design of our entity and schema.

So this is why it's very important to use DTOs to have the interaction with the clients and save the entities in the DB
using mapper class or we could write our own mapper.



____________________
fetchRestaurantById method:
  Optional<Restaurant> restaurant =  restaurantRepo.findById(id);
The return type for this is an optional of restaurant because since there might be a chance that we might not
find that restaurant by ID, hence we always have the option of restaurant in the return type.
*/