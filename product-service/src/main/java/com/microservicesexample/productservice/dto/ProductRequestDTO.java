package com.microservicesexample.productservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductRequestDTO {

    @NotNull(message = "Product name is mandatory!")
    @NotBlank(message = "Product name can't be blank!")
    private String name;
    private String description;
    @NotNull(message = "Product price is mandatory!")
    @PositiveOrZero(message = "Product price should be positive or zero!")
    private BigDecimal price;
}
