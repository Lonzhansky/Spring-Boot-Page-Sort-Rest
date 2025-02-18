package com.example.Spring_Boot_Page_Sort_Rest.dto.product;

import com.example.Spring_Boot_Page_Sort_Rest.entity.Product;
import org.springframework.http.HttpStatus;

public record ProductDtoCreateResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        Product product) {

    public static final String SUCCESS_MESSAGE = "Product has been created successfully.";
    public static final String FAILURE_MESSAGE = "Product has not been created!";

    public static ProductDtoCreateResponse of(boolean isProductCreated, Product product) {
        return isProductCreated ?
                new ProductDtoCreateResponse(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase(),
                        true, SUCCESS_MESSAGE, product) :
                new ProductDtoCreateResponse(
                        HttpStatus.NO_CONTENT.value(),
                        HttpStatus.NO_CONTENT.getReasonPhrase(),
                        false, FAILURE_MESSAGE, null);
    }
}
