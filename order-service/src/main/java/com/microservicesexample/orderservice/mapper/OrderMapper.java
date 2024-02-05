package com.microservicesexample.orderservice.mapper;

import com.microservicesexample.orderservice.dto.OrderItemRequestDTO;
import com.microservicesexample.orderservice.entity.OrderItem;

public class OrderMapper {
    public static OrderItem OrderRequestDTOToOrder(OrderItemRequestDTO orderItemRequestDTO) {
        return OrderItem
                .builder()
                .skuCode(orderItemRequestDTO.getSkuCode())
                .price(orderItemRequestDTO.getPrice())
                .quantity(orderItemRequestDTO.getQuantity())
                .build();
    }
}
