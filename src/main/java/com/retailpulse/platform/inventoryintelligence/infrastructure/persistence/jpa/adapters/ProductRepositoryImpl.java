package com.retailpulse.platform.inventoryintelligence.infrastructure.persistence.jpa.adapters;

import com.retailpulse.platform.inventoryintelligence.domain.model.aggregates.Product;
import com.retailpulse.platform.inventoryintelligence.domain.repositories.ProductRepository;
import com.retailpulse.platform.inventoryintelligence.infrastructure.persistence.jpa.assemblers.ProductPersistenceAssembler;
import com.retailpulse.platform.inventoryintelligence.infrastructure.persistence.jpa.repositories.ProductPersistenceRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductPersistenceRepository repository;

    public ProductRepositoryImpl(ProductPersistenceRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll().stream().map(ProductPersistenceAssembler::toDomainFromPersistence).toList();
    }

    public Optional<Product> findById(String id) {
        return repository.findById(id).map(ProductPersistenceAssembler::toDomainFromPersistence);
    }

    public List<Product> searchByQuery(String query) {
        String safeQuery = query == null ? "" : query;
        return repository.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrZoneNameContainingIgnoreCase(safeQuery, safeQuery, safeQuery)
            .stream()
            .map(ProductPersistenceAssembler::toDomainFromPersistence)
            .toList();
    }

    public Product save(Product product) {
        return ProductPersistenceAssembler.toDomainFromPersistence(repository.save(ProductPersistenceAssembler.toPersistenceFromDomain(product)));
    }

    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
