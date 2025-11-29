package com.cafePos.domain.catalog;

import com.cafePos.domain.common.Product;

import java.util.Optional;
public interface Catalog {
    void add(Product p);
    Optional<Product> findById(String id);
}
