package com.microservicesexample.productservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {
    @Id
    private String id;
    @NotNull(message = "Product name is mandatory!")
    @NotBlank(message = "Product name can't be blank!")
    private String name;
    private String description;
    @NotNull(message = "Product price is mandatory!")
    @PositiveOrZero(message = "Product price should be positive or zero!")
    private BigDecimal price;
}
