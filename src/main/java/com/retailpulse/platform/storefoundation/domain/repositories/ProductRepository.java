package com.retailpulse.platform.storefoundation.domain.repositories;

import com.retailpulse.platform.storefoundation.domain.model.aggregates.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    List<Product> findAll();
    Optional<Product> findById(Long productId);
    List<Product> searchByNameOrSku(String query);
    void deleteById(Long productId);
    long count();
}
