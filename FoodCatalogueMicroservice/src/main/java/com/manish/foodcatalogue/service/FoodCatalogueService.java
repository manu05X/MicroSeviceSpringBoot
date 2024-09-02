//package com.manish.foodcatalogue.service;
//
//import com.manish.foodcatalogue.dto.FoodCataloguePage;
//import com.manish.foodcatalogue.dto.FoodItemDTO;
//import com.manish.foodcatalogue.dto.Restaurant;
//import com.manish.foodcatalogue.entity.FoodItem;
//import com.manish.foodcatalogue.mapper.FoodItemMapper;
//import com.manish.foodcatalogue.repo.FoodItemRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class FoodCatalogueService {
//    private FoodItemRepo foodItemRepo;
//    private FoodItemMapper foodItemMapper;
//
//    @Autowired
//    public FoodCatalogueService(FoodItemRepo foodItemRepo, FoodItemMapper foodItemMapper) {
//        this.foodItemRepo = foodItemRepo;
//        this.foodItemMapper = foodItemMapper;
//    }
//
//    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
//        FoodItem foodItem = foodItemMapper.mapFoodItemDTOToFoodItem(foodItemDTO);
//        FoodItem foodItemSavedInDB = foodItemRepo.save(foodItem);
//        return foodItemMapper.mapFoodItemToFoodItemDTO(foodItemSavedInDB);
//    }
//
//    public FoodCataloguePage fetchFoodCataloguePageDetails(Integer restaurantId) {
//        List<FoodItem> foodItemList =  fetchFoodItemList(restaurantId);
//        Restaurant restaurant = fetchRestaurantDetailsFromRestaurantMS(restaurantId);
//        return createFoodCataloguePage(foodItemList, restaurant);
//    }
//
//    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
//        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
//        foodCataloguePage.setFoodItemsList(foodItemList);
//        foodCataloguePage.setRestaurant(restaurant);
//        return foodCataloguePage;
//    }
//
//    private Restaurant fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
//        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/"+restaurantId, Restaurant.class);
//    }
//
//    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
//        return foodItemRepo.findByRestaurantId(restaurantId);
//    }
//}

package com.manish.foodcatalogue.service;

import com.manish.foodcatalogue.dto.FoodCataloguePage;
import com.manish.foodcatalogue.dto.FoodItemDTO;
import com.manish.foodcatalogue.dto.Restaurant;
import com.manish.foodcatalogue.entity.FoodItem;
import com.manish.foodcatalogue.mapper.FoodItemMapper;
import com.manish.foodcatalogue.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {
    private FoodItemRepo foodItemRepo;
    private FoodItemMapper foodItemMapper;
    private RestTemplate restTemplate;

    @Autowired
    public FoodCatalogueService(FoodItemRepo foodItemRepo, FoodItemMapper foodItemMapper, RestTemplate restTemplate) {
        this.foodItemRepo = foodItemRepo;
        this.foodItemMapper = foodItemMapper;
        this.restTemplate = restTemplate;
    }

    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
        FoodItem foodItem = foodItemMapper.mapFoodItemDTOToFoodItem(foodItemDTO);
        FoodItem foodItemSavedInDB = foodItemRepo.save(foodItem);
        return foodItemMapper.mapFoodItemToFoodItemDTO(foodItemSavedInDB);
    }


    public FoodCataloguePage fetchFoodCataloguePageDetails(Integer restaurantId) {
        //1> Food item list : which can come from our own DB and the restaurant details
        //2>  Restaurant details, which will come from restaurantListing microservice that we have created earlier

        List<FoodItem> foodItemList = fetchFoodItemList(restaurantId);
        Restaurant restaurant = fetchRestaurantDetailsFromRestaurantMS(restaurantId);
        //Combines these two pieces of data into a FoodCataloguePage object, which is then returned.
        return createFoodCataloguePage(foodItemList, restaurant);
    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
        /*Now here the No argument constructor comes into picture.
        If you would have just overridden the AllArgument constructor and not use the NoArgument constructor, in FoodCataloguePage
        you would not have been able to use this.
         So whenever you override the argument constructor always override no argument constructor.
        */
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItemsList(foodItemList);
        foodCataloguePage.setRestaurant(restaurant);
        return foodCataloguePage;
        /*
            Now you can happily return what you get from this createFoodCataloguePage because it has both the things in place,
            the FoodItemsList and the RestaurantDetails one coming from your own database, one coming from hitting the microservice restaurantListing.
        */
    }

    //this method is going to hit our restaurant listing microservice and fetch the restaurant details based on the restaurant ID.
    //So whatever response you get from the restaurantListing microservice will be mapped to restaurant.class in our application.
    private Restaurant fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/"+restaurantId, Restaurant.class);
    }
    /*
    You should never use localhost 9091, but the name of the service that is RESTAURANT-SERVICE that you have given here.
    The URL : "http://RESTAURANT-SERVICE/restaurant/fetchById/"+restaurantId, Restaurant.class.
    The restaurant service is the name. Since there can be multiple instances at different ports and different instances running,
    you should always use the load balanced URL format to hit the particular microservice.

    You can do it localhost 9091 but that will not fulfill your request when you have load balanced multiple
    instances running on Eureka on cloud. So do it in a better way.

    So this is how you hit a load balanced microservice registered to Eureka and leave it to Eureka, which can
    automatically resolve it to localhost 9091 When you use restaurant service(RESTAURANT-SERVICE) as your URL.
    */

    //retrieve a list of FoodItem objects associated with a specific restaurantId
    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId);
    }


}

/*
The code provided implements a modular approach to fetching data from two sources (a database and a microservice) and combining them into a single response object (FoodCataloguePage). Let's break down the purpose and functionality of each method:

1. fetchFoodCataloguePageDetails(Integer restaurantId)
This method is the primary public method that orchestrates the entire process. It performs the following tasks:

Fetches a list of food items associated with a specific restaurant from the database.
Fetches the restaurant details from another microservice.
Combines these two pieces of data into a FoodCataloguePage object, which is then returned.
This method adheres to the Single Responsibility Principle (SRP), which means it has one primary job: to fetch and combine the food items and restaurant details into a cohesive response.

2. createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant)
This method is a private helper method that takes two inputs:

A list of FoodItem objects.
A Restaurant object.
It combines these inputs into a FoodCataloguePage object, which is a custom data structure designed to hold both the list of food items and the restaurant details. This method is responsible only for merging these two inputs into one cohesive response object, keeping it focused on a single task.

3. fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId)
This method is another private helper method responsible for:

Communicating with another microservice (in this case, RESTAURANT-SERVICE) to fetch details of the restaurant identified by restaurantId.
It sends a GET request to the microservice and retrieves the restaurant details in the form of a Restaurant object.
This method’s single responsibility is to fetch restaurant details from an external service, keeping the code modular and readable.

4. fetchFoodItemList(Integer restaurantId)
This method is also a private helper method that:

Queries the database to retrieve a list of FoodItem objects associated with a specific restaurantId.
It uses a repository (foodItemRepo) to perform the database operation and returns a list of FoodItem objects.
Its single responsibility is to fetch food items from the local database, adhering to SRP.

Summary of the Modularization and SRP:
The idea behind breaking down the logic into these methods is to follow the Single Responsibility Principle (SRP), which ensures that each method has one, and only one, reason to change. This modular approach makes the code more readable and maintainable.

fetchFoodCataloguePageDetails: Coordinates the process of fetching and combining data.
createFoodCataloguePage: Combines the food items and restaurant details into a single response.
fetchRestaurantDetailsFromRestaurantMS: Fetches restaurant details from an external service.
fetchFoodItemList: Fetches a list of food items from the database.
By keeping each method focused on a single responsibility, the code is more organized and easier to test, making it easier to understand what each part of the code is doing.


_______________________________________________________________________________________________________________



1. Purpose of RestTemplate:
Fetching Data: RestTemplate is a synchronous client used to perform HTTP requests and handle responses in a Spring Boot application. It's used to make calls to other microservices and fetch data.
Microservice Communication: In a microservices architecture, different services often need to communicate with each other. RestTemplate helps in this communication by allowing you to send HTTP requests and process responses.
2. Load Balancing with Eureka:
Multiple Instances: When you have multiple instances of a microservice (e.g., restaurant listing microservice), you need a mechanism to balance the load between these instances. Load balancing ensures that requests are distributed evenly across all available instances.
Eureka: Eureka is a service registry that helps in managing and discovering microservice instances. It keeps track of all available instances of a service.
3. Spring Cloud Load Balancer Integration:
@LoadBalanced Annotation: In a Spring Boot application, you can use the @LoadBalanced annotation on a RestTemplate bean to enable client-side load balancing. This annotation allows RestTemplate to work with Eureka for load balancing requests.
Automatic Load Balancing: With @LoadBalanced, Spring Cloud automatically handles the task of selecting an appropriate instance of the microservice based on the load and availability. This is done internally by Eureka and Spring Cloud Load Balancer.
4. Example Bean Configuration:

@Bean
@LoadBalanced
public RestTemplate restTemplate() {
    return new RestTemplate();
}
Bean Definition: This method defines a RestTemplate bean and annotates it with @LoadBalanced. This tells Spring to configure the RestTemplate to support client-side load balancing.
Load Balancing: When you use this RestTemplate to make a request to a microservice, Spring Cloud Load Balancer will handle the routing of the request to one of the available instances of the microservice.
5. How It Works:
Service Discovery: Eureka keeps track of the different instances of each microservice. When a service registers itself with Eureka, it provides metadata such as its hostname, port, and health status.
Load Balancer Integration: When you use @LoadBalanced, RestTemplate will interact with Eureka to get a list of available instances of the target microservice. It then selects one instance to handle the request based on load balancing algorithms (e.g., round-robin).
Summary:
RestTemplate is used for making HTTP requests between microservices.
@LoadBalanced allows RestTemplate to use client-side load balancing, leveraging Eureka for instance discovery and request routing.
Load Balancing ensures that requests are distributed across multiple instances of a microservice, improving availability and scalability.
By using @LoadBalanced with RestTemplate, you ensure that your application can efficiently interact with multiple instances of a microservice, handling requests in a balanced manner and avoiding overloading any single instance.


 */