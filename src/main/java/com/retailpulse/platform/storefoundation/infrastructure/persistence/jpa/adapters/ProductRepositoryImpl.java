package com.retailpulse.platform.storefoundation.infrastructure.persistence.jpa.adapters;

import com.retailpulse.platform.storefoundation.domain.model.aggregates.Product;
import com.retailpulse.platform.storefoundation.domain.repositories.ProductRepository;
import com.retailpulse.platform.storefoundation.infrastructure.persistence.jpa.assemblers.ProductPersistenceAssembler;
import com.retailpulse.platform.storefoundation.infrastructure.persistence.jpa.repositories.ProductPersistenceRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductPersistenceRepository repository;
    private final ProductPersistenceAssembler assembler = new ProductPersistenceAssembler();
    public ProductRepositoryImpl(ProductPersistenceRepository repository) { this.repository = repository; }
    public Product save(Product product) { return assembler.toDomain(repository.save(assembler.toEntity(product))); }
    public List<Product> findAll() { return repository.findAll().stream().map(assembler::toDomain).toList(); }
    public Optional<Product> findById(Long productId) { return repository.findById(productId).map(assembler::toDomain); }
    public List<Product> searchByNameOrSku(String query) { return repository.findByNameContainingIgnoreCaseOrSkuContainingIgnoreCase(query, query).stream().map(assembler::toDomain).toList(); }
    public void deleteById(Long productId) { repository.deleteById(productId); }
    public long count() { return repository.count(); }
}
