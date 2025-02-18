package com.example.Spring_Boot_Page_Sort_Rest.dto.product;

import com.example.Spring_Boot_Page_Sort_Rest.entity.Product;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

public record ProductDtoListResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        List<Product> productList) {

    public static final String SUCCESS_MESSAGE = "Product list has been fetched successfully.";
    public static final String FAILURE_MESSAGE = "Product list has not been found!";

    public static ProductDtoListResponse of(boolean isProductListEmpty, List<Product> list) {
        return isProductListEmpty ?
                new ProductDtoListResponse(
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        false, FAILURE_MESSAGE, Collections.emptyList()) :
                new ProductDtoListResponse(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase(),
                        true, SUCCESS_MESSAGE, list);
    }
}
