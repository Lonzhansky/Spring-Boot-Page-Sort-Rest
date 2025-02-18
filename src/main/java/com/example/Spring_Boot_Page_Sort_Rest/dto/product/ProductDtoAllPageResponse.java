package com.example.Spring_Boot_Page_Sort_Rest.dto.product;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Map;

public record ProductDtoAllPageResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        Map<String, Object> data) {

    public static final String SUCCESS_MESSAGE = "Products data has been fetched successfully.";
    public static final String FAILURE_MESSAGE = "Products data has not been found!";

    public static ProductDtoAllPageResponse of(boolean isProductDataEmpty, Map<String, Object> data) {
        return isProductDataEmpty ?
                new ProductDtoAllPageResponse(
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        false, FAILURE_MESSAGE, Collections.emptyMap()) :
                new ProductDtoAllPageResponse(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase(),
                        true, SUCCESS_MESSAGE, data);
    }
}
