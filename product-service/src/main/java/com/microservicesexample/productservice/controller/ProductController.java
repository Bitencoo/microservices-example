package com.microservicesexample.productservice.controller;

import com.microservicesexample.productservice.dto.ProductRequestDTO;
import com.microservicesexample.productservice.dto.ProductResponseDTO;
import com.microservicesexample.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
        productService.save(productRequestDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK) ;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable("productId") String productId) {
        return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.FOUND);
    }
}
