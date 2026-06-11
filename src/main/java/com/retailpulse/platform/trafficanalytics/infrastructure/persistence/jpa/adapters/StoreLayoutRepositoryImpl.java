package com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.adapters;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.StoreLayout;
import com.retailpulse.platform.trafficanalytics.domain.repositories.StoreLayoutRepository;
import com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.assemblers.StoreLayoutPersistenceAssembler;
import com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.repositories.StoreLayoutPersistenceRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class StoreLayoutRepositoryImpl implements StoreLayoutRepository {
    private final StoreLayoutPersistenceRepository repository;

    public StoreLayoutRepositoryImpl(StoreLayoutPersistenceRepository repository) {
        this.repository = repository;
    }

    public List<StoreLayout> findAll() {
        return repository.findAll().stream().map(StoreLayoutPersistenceAssembler::toDomainFromPersistence).toList();
    }

    public Optional<StoreLayout> findById(String id) {
        return repository.findById(id).map(StoreLayoutPersistenceAssembler::toDomainFromPersistence);
    }

    public Optional<StoreLayout> findCurrent() {
        return repository.findFirstByActiveTrue().map(StoreLayoutPersistenceAssembler::toDomainFromPersistence);
    }

    public StoreLayout save(StoreLayout storeLayout) {
        return StoreLayoutPersistenceAssembler.toDomainFromPersistence(repository.save(StoreLayoutPersistenceAssembler.toPersistenceFromDomain(storeLayout)));
    }

    public boolean existsById(String id) {
        return repository.existsById(id);
    }
}
