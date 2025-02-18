package com.example.Spring_Boot_Page_Sort_Rest.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T,S> {
    T create(S request);
    Optional<List<T>> getAll();
    T getById(Long id);
    T updateById(Long id, S request);
    boolean deleteById(Long id);
}
