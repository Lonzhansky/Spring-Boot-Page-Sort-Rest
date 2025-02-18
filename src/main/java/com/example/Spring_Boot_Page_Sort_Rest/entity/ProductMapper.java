package com.example.Spring_Boot_Page_Sort_Rest.entity;

import com.example.Spring_Boot_Page_Sort_Rest.dto.product.ProductDtoRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product dtoCreateToEntity(ProductDtoRequest request) {
        Product product = new Product();

        Long id = request.id();
        if (id != null) product.setId(id);

        String productName = request.name();
        if (productName != null) {
            if (!productName.isBlank())
                product.setName(productName);
        }

        String description = request.description();
        if (description != null) {
            if (!description.isBlank())
                product.setDescription(description);
        }

        Boolean isInStock = request.inStock();
        if (isInStock != null) product.setInStock(isInStock);

        return product;
    }

    public Product dtoUpdateToEntity(Long id, ProductDtoRequest request,
                                  Product productToUpdate) {

        if (id != null) productToUpdate.setId(id);

        String productName = request.name();
        if (productName != null) {
            if (!productName.isBlank())
                productToUpdate.setName(productName);
        }

        String description = request.description();
        if (description != null) {
            if (!description.isBlank())
                productToUpdate.setDescription(description);
        }

        Boolean isInStock = request.inStock();
        if (isInStock != null) productToUpdate.setInStock(isInStock);

        return productToUpdate;
    }
}
