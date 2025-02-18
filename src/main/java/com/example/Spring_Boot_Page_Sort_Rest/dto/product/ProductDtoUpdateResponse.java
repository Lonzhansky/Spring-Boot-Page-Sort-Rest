package com.example.Spring_Boot_Page_Sort_Rest.dto.product;

import com.example.Spring_Boot_Page_Sort_Rest.entity.Product;
import org.springframework.http.HttpStatus;

public record ProductDtoUpdateResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        Product product) {

    public static final String SUCCESS_MESSAGE = "Product with id %s has been updated successfully.";
    public static final String FAILURE_MESSAGE = "Product with id %s has not been found!";

    public static ProductDtoUpdateResponse of(Long id, boolean isProductFound, Product productUpdated) {
        return isProductFound ?
                new ProductDtoUpdateResponse(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase(),
                        true, SUCCESS_MESSAGE.formatted(id), productUpdated) :
                new ProductDtoUpdateResponse(
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        false, FAILURE_MESSAGE.formatted(id), null);
    }
}
