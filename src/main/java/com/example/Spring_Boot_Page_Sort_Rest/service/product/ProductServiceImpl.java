package com.example.Spring_Boot_Page_Sort_Rest.service.product;

import com.example.Spring_Boot_Page_Sort_Rest.dto.product.ProductDtoRequest;
import com.example.Spring_Boot_Page_Sort_Rest.entity.Product;
import com.example.Spring_Boot_Page_Sort_Rest.entity.ProductMapper;
import com.example.Spring_Boot_Page_Sort_Rest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.StreamSupport;

@Qualifier("productServiceImpl")
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper mapper;
    @Autowired
    @Qualifier("productRepository")
    private ProductRepository productRepository;

    @Override
    public Product create(ProductDtoRequest request) {
        Objects.requireNonNull(request,
                "Parameter [request] must not be null!");
        Product product = mapper.dtoCreateToEntity(request);
        return productRepository.save(product);
    }

    @Override
    public Optional<List<Product>> getAll() {
        Iterable<Product> iterable = productRepository.findAll();
        // Конвертуємо Iterable в List,
        // оскільки interface CrudRepository<T, ID>
        // має саме метод Iterable<T> findAll();
        List<Product> list =
                StreamSupport.stream(iterable.spliterator(), false)
                        .toList();
        // Запаковуємо List в Optional та повертаємо
        return Optional.of(list);
    }

    @Override
    public Product getById(Long id) {
        Objects.requireNonNull(id,
                "Parameter [id] must not be null!");
        return productRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Product updateById(Long id, ProductDtoRequest request) {
        Objects.requireNonNull(request,
                "Parameter [request] must not be null!");
        if (id == null) {
            throw new IllegalArgumentException("Id must be provided!");
        }
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            Product productToUpdate =
                    mapper.dtoUpdateToEntity(id, request,
                            optional.get());
            productRepository.save(productToUpdate);
        }
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        Objects.requireNonNull(id,
                "Parameter [id] must not be null!");
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    @Override
    public Map<String, Object> getAllProductsPage(
            String name, int page, int size, String[] sort) {
        try {
            List<Sort.Order> orders = new ArrayList<>();

            if (sort[0].contains(",")) {
                // will sort more than 2 fields
                // sortOrder="field, direction"
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(
                            new Sort.Order(getSortDirection(_sort[1]), _sort[0])
                    );
                }
            } else {
                // sort=[field, direction]
                orders.add(
                        new Sort.Order(getSortDirection(sort[1]), sort[0]));
            }

            List<Product> products;
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));
            Page<Product> pageProds;
            if (name == null)
                pageProds = productRepository.findAll(pagingSort);
            else
                pageProds = productRepository.findByName(name, pagingSort);
            products = pageProds.getContent();

            Map<String, Object> map = new HashMap<>();
            map.put("products", products);
            map.put("currentPage", pageProds.getNumber());
            map.put("totalItems", pageProds.getTotalElements());
            map.put("totalPages", pageProds.getTotalPages());
            return map;
        } catch (Exception e) {
            return Collections.emptyMap();
        }
    }

    @Override
    public Map<String, Object> findByInStock(
            int page, int size) {
        try {
            List<Product> products;
            Pageable paging = PageRequest.of(page, size);
            Page<Product> pageProds =
                    productRepository.findByInStock(true, paging);
            products = pageProds.getContent();
            Map<String, Object> map = new HashMap<>();
            map.put("products", products);
            map.put("currentPage", pageProds.getNumber());
            map.put("totalItems", pageProds.getTotalElements());
            map.put("totalPages", pageProds.getTotalPages());
            return map;
        } catch (Exception e) {
            return Collections.emptyMap();
        }
    }

}
