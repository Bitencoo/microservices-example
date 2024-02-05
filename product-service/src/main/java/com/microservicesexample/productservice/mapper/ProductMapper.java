package com.microservicesexample.productservice.mapper;

import com.microservicesexample.productservice.dto.ProductResponseDTO;
import com.microservicesexample.productservice.model.Product;
import org.springframework.beans.BeanUtils;

public class ProductMapper {
    public static ProductResponseDTO produtctToProductResponseDTO(Product product){
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        BeanUtils.copyProperties(product, productResponseDTO);
        return productResponseDTO;
    }
}
