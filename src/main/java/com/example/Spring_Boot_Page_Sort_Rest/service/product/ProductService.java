package com.example.Spring_Boot_Page_Sort_Rest.service.product;

import com.example.Spring_Boot_Page_Sort_Rest.dto.product.ProductDtoRequest;
import com.example.Spring_Boot_Page_Sort_Rest.entity.Product;
import com.example.Spring_Boot_Page_Sort_Rest.service.BaseService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface ProductService extends BaseService<Product, ProductDtoRequest> {
    Product create(ProductDtoRequest request);

    Optional<List<Product>> getAll();

    Product getById(Long id);

    Product updateById(Long id, ProductDtoRequest request);

    boolean deleteById(Long id);

    Sort.Direction getSortDirection(String direction);

    Map<String, Object> getAllProductsPage(
            String name, int page, int size, String[] sort);

    Map<String, Object> findByInStock(
            int page, int size);
}
