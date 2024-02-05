package com.microservicesexample.productservice.exceptions;

import com.microservicesexample.productservice.utils.ExceptionUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiErrorResponse> handleNoSuchElementFoundException(
            NoSuchElementException ex, HttpServletRequest request) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse
                .builder()
                .url(request.getRequestURI().toString())
                .timestamp(LocalDateTime.now().toString())
                .message(
                        MessageFormat.format("Product {0} not Found!",
                            ExceptionUtils.extractProductIdFromURI(request.getRequestURI().toString())
                        )
                )
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build();
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FieldsApiErrorMessage> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, HttpServletRequest request
    ) {
        FieldsApiErrorMessage fieldsApiErrorMessage = FieldsApiErrorMessage
                .builder()
                .url(request.getRequestURI().toString())
                .timestamp(LocalDateTime.now().toString())
                .fieldsErrors(ExceptionUtils.extractFieldsErrors(ex.getFieldErrors()))
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        return new ResponseEntity<FieldsApiErrorMessage>(fieldsApiErrorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex, HttpServletRequest request) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse
                .builder()
                .url(request.getRequestURI().toString())
                .timestamp(LocalDateTime.now().toString())
                .message(
                        MessageFormat.format("Field {0} not allowed on the body request",
                            ExceptionUtils.extractNotAllowedFieldNameBetweenDoubleQuotes(ex.getMessage())
                        )
                )
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
