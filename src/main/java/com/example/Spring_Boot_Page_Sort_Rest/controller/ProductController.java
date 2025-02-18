package com.example.Spring_Boot_Page_Sort_Rest.controller;

import com.example.Spring_Boot_Page_Sort_Rest.dto.product.*;
import com.example.Spring_Boot_Page_Sort_Rest.entity.Product;
import com.example.Spring_Boot_Page_Sort_Rest.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    @Qualifier("productServiceImpl")
    ProductService productService;

    private Sort.Direction getSortDirection(String direction) {
        return productService.getSortDirection(direction);
    }

    @GetMapping("/pages")
    public ResponseEntity<ProductDtoAllPageResponse> getAllProductsPage(
            @RequestParam(required = false) String name,
            // Поточна сторінка 0
            @RequestParam(defaultValue = "0") int page,
            // Кількість елементів на сторінку 4
            @RequestParam(defaultValue = "4") int size,
            // Сортування за id в низпадаючому порядку
            @RequestParam(defaultValue = "id,desc") String[] sort) {
        Map<String, Object> map =
                productService.getAllProductsPage(name, page, size, sort);
        return (!map.isEmpty()) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoAllPageResponse.of(false,
                                map)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoAllPageResponse.of(true,
                                Collections.emptyMap()));
    }

    @GetMapping("/in-stock")
    public ResponseEntity<ProductDtoFindByInStockResponse> findByInStock(
            // Поточна сторінка 0
            @RequestParam(defaultValue = "0") int page,
            // Кількість елементів на сторінку 4
            @RequestParam(defaultValue = "4") int size) {
        Map<String, Object> map =
                productService.findByInStock(page, size);
        return (!map.isEmpty()) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoFindByInStockResponse.of(
                                false, map)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoFindByInStockResponse.of(
                                true,
                                Collections.emptyMap()));
    }

    @PostMapping
    public ResponseEntity<ProductDtoCreateResponse> createProduct(
            @RequestBody ProductDtoRequest request) {
        Product product = productService.create(request);
        // ternary operator usage
        return (product != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoCreateResponse.of(true,
                                product)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoCreateResponse.of(false,
                                null));
    }

    @GetMapping
    public ResponseEntity<ProductDtoListResponse> getAllProducts() {
        Optional<List<Product>> optional = productService.getAll();
        if (optional.isPresent()) {
            List<Product> list = optional.get();
            return (!list.isEmpty()) ?
                    ResponseEntity.status(HttpStatus.OK)
                            .body(ProductDtoListResponse.of(false,
                                    list)) :
                    ResponseEntity.status(HttpStatus.OK)
                            .body(ProductDtoListResponse.of(true,
                                    Collections.emptyList()));
        } else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ProductDtoListResponse.of(true,
                            Collections.emptyList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDtoGetByIdResponse> getProductById(
            @PathVariable("id") Long id) {
        Product product = productService.getById(id);
        return (product != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoGetByIdResponse.of(id, true,
                                product)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoGetByIdResponse.of(id, false,
                                null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDtoUpdateResponse> updateProductById(
            @PathVariable("id") Long id,
            @RequestBody ProductDtoRequest request) {
        return (productService.getById(id) != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoUpdateResponse.of(id, true,
                                productService.updateById(id, request))) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoUpdateResponse.of(id, false,
                                null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDtoDeleteResponse> deleteProductById(
            @PathVariable(value = "id") Long id) {
        return (productService.deleteById(id)) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoDeleteResponse.of(id, true)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoDeleteResponse.of(id, false));
    }

}
