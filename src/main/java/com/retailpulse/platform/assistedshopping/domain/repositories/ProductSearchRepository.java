package com.retailpulse.platform.assistedshopping.domain.repositories;

import com.retailpulse.platform.assistedshopping.domain.model.entities.ProductSearch;
import java.util.List;

public interface ProductSearchRepository {
    ProductSearch save(ProductSearch search);
    List<ProductSearch> findAll();
    List<ProductSearch> findByProductId(Long productId);
    long count();
}
