package com.retailpulse.platform.storefoundation.application.internal.queryservices;

import com.retailpulse.platform.storefoundation.application.queryservices.ProductQueryService;
import com.retailpulse.platform.storefoundation.domain.model.aggregates.Product;
import com.retailpulse.platform.storefoundation.domain.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {
    private final ProductRepository repository;
    public ProductQueryServiceImpl(ProductRepository repository) { this.repository = repository; }
    public List<Product> handleGetAll() { return repository.findAll(); }
    public Optional<Product> handleGetById(Long productId) { return repository.findById(productId); }
    public List<Product> handleSearch(String query) { return repository.searchByNameOrSku(query == null ? "" : query); }
}
