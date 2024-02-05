package com.microservicesexample.orderservice.controller;

import com.microservicesexample.orderservice.dto.OrderRequestDTO;
import com.microservicesexample.orderservice.dto.OrderResponseDTO;
import com.microservicesexample.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity placeOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO orderResponseDTO = orderService.placeOrder(orderRequestDTO);
        return new ResponseEntity(orderResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable("orderId") String orderId) {
        return new ResponseEntity(null, HttpStatus.CREATED);
    }
}
