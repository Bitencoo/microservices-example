package com.microservicesexample.productservice.unittests.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservicesexample.productservice.controller.ProductController;
import com.microservicesexample.productservice.dto.ProductRequestDTO;
import com.microservicesexample.productservice.dto.ProductResponseDTO;
import com.microservicesexample.productservice.exceptions.ApiErrorResponse;
import com.microservicesexample.productservice.exceptions.FieldsApiErrorMessage;
import com.microservicesexample.productservice.service.ProductServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

@WebMvcTest(value = ProductController.class)
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    ProductServiceImpl productService;

    @Test
    void givenValidProduct_whenCreateProduct_thenProductCreated() throws Exception {
        ProductRequestDTO productRequestDTO = ProductRequestDTO
                .builder()
                .name("Test Product 001")
                .description("My Test Product for Creating a Valid Product")
                .price(new BigDecimal("199.99"))
                .build();

        doNothing().when(productService).save(any());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productRequestDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void givenNotValidProduct_whenCreateProduct_thenBadRequestReturned() throws Exception {
        ProductRequestDTO productRequestDTO = ProductRequestDTO
                .builder()
                .name("")
                .description("My Test Product for Creating a Valid Product")
                .price(null)
                .build();

        doNothing().when(productService).save(any());
        String responseContent = mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productRequestDTO)))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        FieldsApiErrorMessage fieldsApiErrorMessage = objectMapper.readValue(responseContent, FieldsApiErrorMessage.class);
        assertNotNull(fieldsApiErrorMessage);
        assertEquals(HttpStatus.BAD_REQUEST.value(), fieldsApiErrorMessage.getStatusCode());
        assertEquals(2, fieldsApiErrorMessage.getFieldsErrors().size());

    }

    @Test
    void givenRequestWithAdditionalFields_whenCreateProduct_thenBadRequestReturned() throws Exception {
        ProductRequestDTO productRequestDTO = ProductRequestDTO
                .builder()
                .name("Test Product 001")
                .description("My Test Product for Creating a Valid Product")
                .price(new BigDecimal("199.99"))
                .build();

        String additionalField = "\"additionalField\":\"Additional Value\"";
        String content = objectMapper.writeValueAsString(productRequestDTO);
        content = content.substring(0, content.length() - 1) + "," + additionalField + "}";

        ApiErrorResponse expectedApiErrorResponse = ApiErrorResponse
                .builder()
                .url("/api/products")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("Field additionalField not allowed on the body request")
                .build();
        doNothing().when(productService).save(any());
        String responseContent = mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ApiErrorResponse apiErrorResponse = objectMapper.readValue(responseContent, ApiErrorResponse.class);
        assertNotNull(apiErrorResponse);
        assertEquals(expectedApiErrorResponse.getStatusCode(), apiErrorResponse.getStatusCode());
        assertEquals(expectedApiErrorResponse.getUrl(), apiErrorResponse.getUrl());
        assertEquals(expectedApiErrorResponse.getMessage(), apiErrorResponse.getMessage());
    }
    @Test
    void givenValidProduct_whenGetProductById_thenProductReturned() throws Exception {
        String productId = "65bc4d95aba6984685491551";
        ProductResponseDTO productResponseDTO = ProductResponseDTO
                .builder()
                .id(productId)
                .name("Test Product 001")
                .description("My Test Product for Creating a Valid Product")
                .price(new BigDecimal("199.99"))
                .build();

        when(productService.getProduct(anyString())).thenReturn(productResponseDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/" + productId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productResponseDTO)))
                .andExpect(MockMvcResultMatchers.jsonPath("id", Matchers.is(productId)))
                .andExpect(status().isFound());
    }

    @Test
    void givenNoProductsOnDB_whenGetAllProducts_thenReturnsEmptyList() throws Exception {
        when(productService.getAllProducts()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    void givenProductsOnDB_whenGetAllProducts_thenReturnsListOfProducts() throws Exception {
        String id = "1935251030331";
        ProductResponseDTO productResponseDTO = ProductResponseDTO
                .builder()
                .id(id)
                .name("Product 0001")
                .description("Returned Product 0001")
                .price(new BigDecimal("99.99"))
                .build();
        when(productService.getAllProducts()).thenReturn(Arrays.asList(productResponseDTO));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(id)));
    }

    @Test
    public void givenWrongURL_whenCallingApplication_thenReturned404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/wrong-url")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}