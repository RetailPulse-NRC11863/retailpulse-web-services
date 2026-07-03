package com.retailpulse.platform.assistedshopping.infrastructure.persistence.jpa.adapters;

import com.retailpulse.platform.assistedshopping.domain.model.entities.ProductSearch;
import com.retailpulse.platform.assistedshopping.domain.repositories.ProductSearchRepository;
import com.retailpulse.platform.assistedshopping.infrastructure.persistence.jpa.assemblers.ProductSearchPersistenceAssembler;
import com.retailpulse.platform.assistedshopping.infrastructure.persistence.jpa.repositories.ProductSearchPersistenceRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ProductSearchRepositoryImpl implements ProductSearchRepository {
    private final ProductSearchPersistenceRepository repository;
    private final ProductSearchPersistenceAssembler assembler = new ProductSearchPersistenceAssembler();

    public ProductSearchRepositoryImpl(ProductSearchPersistenceRepository repository) {
        this.repository = repository;
    }

    public ProductSearch save(ProductSearch search) {
        return assembler.toDomain(repository.save(assembler.toEntity(search)));
    }

    public List<ProductSearch> findAll() {
        return repository.findAll().stream().map(assembler::toDomain).toList();
    }

    public List<ProductSearch> findByProductId(Long productId) {
        return repository.findByProductId(productId).stream().map(assembler::toDomain).toList();
    }

    public long count() {
        return repository.count();
    }
}
