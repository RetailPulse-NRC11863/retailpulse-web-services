package com.retailpulse.platform.storefoundation.infrastructure.persistence.jpa.adapters;

import com.retailpulse.platform.storefoundation.domain.model.entities.Zone;
import com.retailpulse.platform.storefoundation.domain.repositories.ZoneRepository;
import com.retailpulse.platform.storefoundation.infrastructure.persistence.jpa.assemblers.ZonePersistenceAssembler;
import com.retailpulse.platform.storefoundation.infrastructure.persistence.jpa.repositories.ZonePersistenceRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ZoneRepositoryImpl implements ZoneRepository {
    private final ZonePersistenceRepository repository;
    private final ZonePersistenceAssembler assembler = new ZonePersistenceAssembler();
    public ZoneRepositoryImpl(ZonePersistenceRepository repository) { this.repository = repository; }
    public Zone save(Zone zone) { return assembler.toDomain(repository.save(assembler.toEntity(zone))); }
    public List<Zone> findAll() { return repository.findAll().stream().map(assembler::toDomain).toList(); }
    public Optional<Zone> findById(Long zoneId) { return repository.findById(zoneId).map(assembler::toDomain); }
    public void deleteById(Long zoneId) { repository.deleteById(zoneId); }
    public long count() { return repository.count(); }
}
