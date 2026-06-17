package com.retailpulse.platform.inventoryintelligence.domain.repositories;

import com.retailpulse.platform.inventoryintelligence.domain.model.aggregates.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    Optional<Product> findById(String id);
    List<Product> searchByQuery(String query);
    Product save(Product product);
    boolean existsById(String id);
    void deleteById(String id);
}
