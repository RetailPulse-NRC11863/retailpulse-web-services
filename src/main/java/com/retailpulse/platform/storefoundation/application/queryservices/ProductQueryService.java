package com.retailpulse.platform.storefoundation.application.queryservices;

import com.retailpulse.platform.storefoundation.domain.model.aggregates.Product;
import java.util.List;
import java.util.Optional;

public interface ProductQueryService {
    List<Product> handleGetAll();
    Optional<Product> handleGetById(Long productId);
    List<Product> handleSearch(String query);
}
