package com.cafePos.catalog;

import com.cafePos.common.Product;

import java.util.Optional;
public interface Catalog {
    void add(Product p);
    Optional<Product> findById(String id);
}
