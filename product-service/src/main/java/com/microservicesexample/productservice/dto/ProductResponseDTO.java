package com.microservicesexample.productservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductResponseDTO {
    @NotNull(message = "Product id can't be null!")
    @NotBlank(message = "Product id can't be blank!")
    private String id;

    @NotNull(message = "Product name is mandatory!")
    @NotBlank(message = "Product name can't be blank!")
    private String name;
    private String description;

    @NotNull(message = "Product price is mandatory!")
    @PositiveOrZero(message = "Product price should be positive or zero!")
    private BigDecimal price;
}
