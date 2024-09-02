package com.manish.order.mapper;

import com.manish.order.dto.OrderDTO;
import com.manish.order.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public static Order mapOrderDTOToOrder(OrderDTO orderDTO) {
        if (orderDTO == null) return null;

        Order order = new Order();
        order.setOrderId(orderDTO.getOrderId());
        order.setFoodItemsList(orderDTO.getFoodItemsList());
        order.setRestaurant(orderDTO.getRestaurant());
        order.setUserDTO(orderDTO.getUserDTO());

        return order;
    }

    public static OrderDTO mapOrderToOrderDTO(Order order) {
        if (order == null) return null;

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setFoodItemsList(order.getFoodItemsList());
        orderDTO.setRestaurant(order.getRestaurant());
        orderDTO.setUserDTO(order.getUserDTO());
        return orderDTO;

    }

}
