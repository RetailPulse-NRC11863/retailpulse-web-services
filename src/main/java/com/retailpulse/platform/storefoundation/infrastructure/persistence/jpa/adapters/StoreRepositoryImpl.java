package com.retailpulse.platform.storefoundation.infrastructure.persistence.jpa.adapters;

import com.retailpulse.platform.storefoundation.domain.model.aggregates.Store;
import com.retailpulse.platform.storefoundation.domain.repositories.StoreRepository;
import com.retailpulse.platform.storefoundation.infrastructure.persistence.jpa.assemblers.StorePersistenceAssembler;
import com.retailpulse.platform.storefoundation.infrastructure.persistence.jpa.repositories.StorePersistenceRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class StoreRepositoryImpl implements StoreRepository {
    private final StorePersistenceRepository repository;
    private final StorePersistenceAssembler assembler = new StorePersistenceAssembler();
    public StoreRepositoryImpl(StorePersistenceRepository repository) { this.repository = repository; }
    public Store save(Store store) { return assembler.toDomain(repository.save(assembler.toEntity(store))); }
    public List<Store> findAll() { return repository.findAll().stream().map(assembler::toDomain).toList(); }
    public Optional<Store> findById(Long storeId) { return repository.findById(storeId).map(assembler::toDomain); }
    public long count() { return repository.count(); }
}
