package com.microservicesexample.orderservice.service;

import com.microservicesexample.orderservice.dto.OrderRequestDTO;
import com.microservicesexample.orderservice.dto.OrderResponseDTO;
import com.microservicesexample.orderservice.entity.Order;
import com.microservicesexample.orderservice.mapper.OrderMapper;
import com.microservicesexample.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;
    @Override
    public OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO) {
        Order order = Order
                .builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderItemList(
                        orderRequestDTO.getOrderItemRequestDTOList().stream()
                                .map(OrderMapper::OrderRequestDTOToOrder)
                                .collect(Collectors.toList())
                )
                .build();

        Boolean hasinventory = webClient.get().uri("http://localhost:8082/api/inventory/00001")
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        OrderResponseDTO orderResponseDTO = OrderResponseDTO
                .builder()
                .orderNumber(orderRepository.save(order).getOrderNumber())
                .build();

        return orderResponseDTO;
    }

    @Override
    public OrderResponseDTO getOrder(String orderId) {
        Optional<Order> order = orderRepository.findByOrderNumber(orderId);
        OrderResponseDTO orderResponseDTO = OrderResponseDTO
                .builder()
                .build();
        return null;
    }
}
