package com.microservicesexample.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderItemRequestDTO {
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
