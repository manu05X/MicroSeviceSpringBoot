package com.manish.order.service;

import com.manish.order.dto.OrderDTO;
import com.manish.order.dto.OrderDTOFromFE;
import com.manish.order.dto.UserDTO;
import com.manish.order.entity.Order;
import com.manish.order.mapper.OrderMapper;
import com.manish.order.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class OrderService {

    private OrderRepo orderRepo;
    private SequenceGenerator sequenceGenerator;
    private RestTemplate restTemplate;
    private OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderRepo orderRepo, SequenceGenerator sequenceGenerator, RestTemplate restTemplate, OrderMapper orderMapper) {
        this.orderRepo = orderRepo;
        this.sequenceGenerator = sequenceGenerator;
        this.restTemplate = restTemplate;
        this.orderMapper = orderMapper;
    }

    public OrderDTO saveOrderInDb(OrderDTOFromFE orderDetails) {
        // Generate a new Order ID
        Integer newOrderID = sequenceGenerator.generateNextOrderId();

        // Fetch user details using the userId from the incoming orderDetails
        UserDTO userDTO = fetchUserDetailsFromUserId(orderDetails.getUserId());

        // Map OrderDTOFromFE to Order entity
        Order orderToBeSaved = new Order();
        orderToBeSaved.setOrderId(newOrderID);
        orderToBeSaved.setFoodItemsList(orderDetails.getFoodItemsList());
        orderToBeSaved.setRestaurant(orderDetails.getRestaurant());
        orderToBeSaved.setUserDTO(userDTO);

        // Save the Order entity to the database
        orderRepo.save(orderToBeSaved);

        // Map the saved Order entity to OrderDTO and return it
        return OrderMapper.mapOrderToOrderDTO(orderToBeSaved);
    }

    private UserDTO fetchUserDetailsFromUserId(Integer userId) {
        return restTemplate.getForObject("http://USER-SERVICE/user/fetchUserById/" + userId, UserDTO.class);
    }
}