package com.manish.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    private int id;
    private String name;
    private String address;
    private String city;
    private String restaurantDescription;
}

/*

we need to create a restaurant data also. And this will be completely same as what we have created in the
restaurant listing. So I'm going to copy paste from there. So open project, restaurantListingService and
there we had restaurant as entity in restaurantListingService.
It was entity because it was saved in database there.
But here in foodcatalogueMS is not going to be an entity because we are not saving this restaurant in our
foodcatalogue microservice database.

Rather, we are just going to hit this microservice and fetch this particular response from restaurantListing service.
Hence this is not going to be an entity here.It is going to be a DTO there because it is just a data
transfer object that happens from restaurantListingService microservice to foodcatalogueMS microservice.

So I'm just going to take these parameters so that whenever I get a response Json response, I should
be able to map it to a particular Pojo or DTO.

So I'm just going to copy this and save it in my restaurant.



*/