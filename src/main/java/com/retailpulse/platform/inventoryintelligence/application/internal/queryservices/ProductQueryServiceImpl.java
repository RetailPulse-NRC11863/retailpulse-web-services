package com.retailpulse.platform.inventoryintelligence.application.internal.queryservices;

import com.retailpulse.platform.inventoryintelligence.application.queryservices.ProductQueryService;
import com.retailpulse.platform.inventoryintelligence.domain.model.aggregates.Product;
import com.retailpulse.platform.inventoryintelligence.domain.model.queries.GetAllProductsQuery;
import com.retailpulse.platform.inventoryintelligence.domain.model.queries.GetProductByIdQuery;
import com.retailpulse.platform.inventoryintelligence.domain.model.queries.SearchProductsQuery;
import com.retailpulse.platform.inventoryintelligence.domain.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {
    private final ProductRepository repository;

    public ProductQueryServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> handle(GetAllProductsQuery query) {
        return repository.findAll();
    }

    public Optional<Product> handle(GetProductByIdQuery query) {
        return repository.findById(query.id());
    }

    public List<Product> handle(SearchProductsQuery query) {
        return repository.searchByQuery(query.query());
    }
}
