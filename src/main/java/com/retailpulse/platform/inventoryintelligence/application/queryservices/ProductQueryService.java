package com.retailpulse.platform.inventoryintelligence.application.queryservices;

import com.retailpulse.platform.inventoryintelligence.domain.model.aggregates.Product;
import com.retailpulse.platform.inventoryintelligence.domain.model.queries.GetAllProductsQuery;
import com.retailpulse.platform.inventoryintelligence.domain.model.queries.GetProductByIdQuery;
import com.retailpulse.platform.inventoryintelligence.domain.model.queries.SearchProductsQuery;
import java.util.List;
import java.util.Optional;

public interface ProductQueryService {
    List<Product> handle(GetAllProductsQuery query);
    Optional<Product> handle(GetProductByIdQuery query);
    List<Product> handle(SearchProductsQuery query);
}
