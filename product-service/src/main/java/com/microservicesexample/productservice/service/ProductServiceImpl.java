package com.microservicesexample.productservice.service;

import com.microservicesexample.productservice.dto.ProductRequestDTO;
import com.microservicesexample.productservice.dto.ProductResponseDTO;
import com.microservicesexample.productservice.mapper.ProductMapper;
import com.microservicesexample.productservice.model.Product;
import com.microservicesexample.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public void save(ProductRequestDTO productRequestDTO) {
        Product product = Product.builder()
                .name(productRequestDTO.getName())
                .description(productRequestDTO.getDescription())
                .price(productRequestDTO.getPrice())
                .build();
        productRepository.save(product);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductMapper::produtctToProductResponseDTO).collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO getProduct(String productId) {
        Optional<Product> product = productRepository.findById(productId);
        return ProductMapper.produtctToProductResponseDTO(product.get());
    }
}
