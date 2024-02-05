package com.microservicesexample.productservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldsApiErrorMessage {
    String timestamp;
    String url;
    int statusCode;
    List<String> fieldsErrors;
}
