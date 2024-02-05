package com.microservicesexample.productservice.utils;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ExceptionUtils {
    public static String extractNotAllowedFieldNameBetweenDoubleQuotes(String errorMessage) {
        int start = errorMessage.indexOf("\"");
        int end = errorMessage.lastIndexOf("\"");
        return errorMessage.substring(start + 1, end);
    }

    public static String extractProductIdFromURI(String URI) {
        String[] arrayURI = URI.split("/");
        return arrayURI[arrayURI.length - 1];
    }

    public static List<String> extractFieldsErrors(List<FieldError> fieldErrors) {
        List<String> fieldsErrors = new ArrayList<>();
        fieldErrors.stream().map(
                fieldError -> fieldsErrors.add(fieldError.getDefaultMessage())
        ).collect(Collectors.toList());
        return fieldsErrors;
    }
}
