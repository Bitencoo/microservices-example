package com.microservicesexample.productservice.service;

import com.microservicesexample.productservice.dto.ProductRequestDTO;
import com.microservicesexample.productservice.dto.ProductResponseDTO;
import com.microservicesexample.productservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    void save(ProductRequestDTO productRequestDTO);
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO getProduct(String productId);
}
