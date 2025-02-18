package com.example.Spring_Boot_Page_Sort_Rest.dto.product;

import org.springframework.http.HttpStatus;

public record ProductDtoDeleteResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message) {

    public static final String SUCCESS_MESSAGE = "Product with id %s has been deleted successfully.";
    public static final String FAILURE_MESSAGE = "Product with id %s has not been found!";

    public static ProductDtoDeleteResponse of(Long id, boolean isProductFound) {
        return isProductFound ?
                new ProductDtoDeleteResponse(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase(),
                        true, SUCCESS_MESSAGE.formatted(id)) :
                new ProductDtoDeleteResponse(
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        false, FAILURE_MESSAGE.formatted(id));
    }
}
