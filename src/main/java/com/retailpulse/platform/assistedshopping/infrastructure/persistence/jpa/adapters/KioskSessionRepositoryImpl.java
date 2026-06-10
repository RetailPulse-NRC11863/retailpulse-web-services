package com.retailpulse.platform.assistedshopping.infrastructure.persistence.jpa.adapters;

import com.retailpulse.platform.assistedshopping.domain.model.aggregates.KioskSession;
import com.retailpulse.platform.assistedshopping.domain.repositories.KioskSessionRepository;
import com.retailpulse.platform.assistedshopping.infrastructure.persistence.jpa.assemblers.KioskSessionPersistenceAssembler;
import com.retailpulse.platform.assistedshopping.infrastructure.persistence.jpa.repositories.KioskSessionPersistenceRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class KioskSessionRepositoryImpl implements KioskSessionRepository {
    private final KioskSessionPersistenceRepository repository;
    private final KioskSessionPersistenceAssembler assembler = new KioskSessionPersistenceAssembler();
    public KioskSessionRepositoryImpl(KioskSessionPersistenceRepository repository) { this.repository = repository; }
    public KioskSession save(KioskSession session) { return assembler.toDomain(repository.save(assembler.toEntity(session))); }
    public Optional<KioskSession> findById(Long sessionId) { return repository.findById(sessionId).map(assembler::toDomain); }
    public long count() { return repository.count(); }
}
