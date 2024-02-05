package com.microservicesexample.orderservice.service;

import com.microservicesexample.orderservice.dto.GetOrderResponseDTO;
import com.microservicesexample.orderservice.dto.OrderRequestDTO;
import com.microservicesexample.orderservice.dto.OrderResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO);

    OrderResponseDTO getOrder(String orderId);
}
