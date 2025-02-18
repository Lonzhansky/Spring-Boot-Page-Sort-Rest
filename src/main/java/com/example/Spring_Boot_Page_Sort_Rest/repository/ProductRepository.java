package com.example.Spring_Boot_Page_Sort_Rest.repository;

import com.example.Spring_Boot_Page_Sort_Rest.entity.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository - варіант @Component, який повідомляє Spring, що це компонент,
// яким має керувати контейнер IoC.
// Зокрема, репозиторії призначені визначення логіки для шару збереження.
//
// JpaRepository розширює PagingAndSortingRepository і CrudRepository. Надає пов’язані з JPA методи.
// https://docs.spring.io/spring-data/data-jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html
// CrudRepository забезпечує CRUD методи.
// https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
// PagingAndSortingRepository надає методи пагінації та сортування записів.
// https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html
//
// JpaRepository приймає клас сутності, а також тип даних ID,
// який він повинен використовувати для запиту.
@Repository
@Qualifier("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Pageable об’єкт передається для надання властивостей пагінації.
    Page<Product> findByInStock(boolean inStock, Pageable pageable);
    Page<Product> findByName(String name, Pageable pageable);
}
