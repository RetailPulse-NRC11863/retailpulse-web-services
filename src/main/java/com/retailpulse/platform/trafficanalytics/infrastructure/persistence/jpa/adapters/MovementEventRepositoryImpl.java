package com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.adapters;

import com.retailpulse.platform.trafficanalytics.domain.model.entities.MovementEvent;
import com.retailpulse.platform.trafficanalytics.domain.repositories.MovementEventRepository;
import com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.assemblers.MovementEventPersistenceAssembler;
import com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.repositories.MovementEventPersistenceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MovementEventRepositoryImpl implements MovementEventRepository {
    private final MovementEventPersistenceRepository repository;
    private final MovementEventPersistenceAssembler assembler = new MovementEventPersistenceAssembler();
    public MovementEventRepositoryImpl(MovementEventPersistenceRepository repository) { this.repository = repository; }
    public MovementEvent save(MovementEvent event) { return assembler.toDomain(repository.save(assembler.toEntity(event))); }
    public long count() { return repository.count(); }
}
