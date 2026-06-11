package com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.adapters;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.LayoutZone;
import com.retailpulse.platform.trafficanalytics.domain.repositories.LayoutZoneRepository;
import com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.assemblers.LayoutZonePersistenceAssembler;
import com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.repositories.LayoutZonePersistenceRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class LayoutZoneRepositoryImpl implements LayoutZoneRepository {
    private final LayoutZonePersistenceRepository repository;

    public LayoutZoneRepositoryImpl(LayoutZonePersistenceRepository repository) {
        this.repository = repository;
    }

    public List<LayoutZone> findAll() {
        return repository.findAll().stream().map(LayoutZonePersistenceAssembler::toDomainFromPersistence).toList();
    }

    public Optional<LayoutZone> findById(String id) {
        return repository.findById(id).map(LayoutZonePersistenceAssembler::toDomainFromPersistence);
    }

    public LayoutZone save(LayoutZone layoutZone) {
        return LayoutZonePersistenceAssembler.toDomainFromPersistence(repository.save(LayoutZonePersistenceAssembler.toPersistenceFromDomain(layoutZone)));
    }

    public boolean existsById(String id) {
        return repository.existsById(id);
    }
}
