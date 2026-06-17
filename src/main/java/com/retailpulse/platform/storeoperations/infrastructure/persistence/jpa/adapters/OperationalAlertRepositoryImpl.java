package com.retailpulse.platform.storeoperations.infrastructure.persistence.jpa.adapters;

import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalAlert;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertStatus;
import com.retailpulse.platform.storeoperations.domain.repositories.OperationalAlertRepository;
import com.retailpulse.platform.storeoperations.infrastructure.persistence.jpa.assemblers.OperationalAlertPersistenceAssembler;
import com.retailpulse.platform.storeoperations.infrastructure.persistence.jpa.repositories.OperationalAlertPersistenceRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class OperationalAlertRepositoryImpl implements OperationalAlertRepository {
    private final OperationalAlertPersistenceRepository repository;
    private final OperationalAlertPersistenceAssembler assembler = new OperationalAlertPersistenceAssembler();

    public OperationalAlertRepositoryImpl(OperationalAlertPersistenceRepository repository) {
        this.repository = repository;
    }

    public List<OperationalAlert> findAll() {
        return repository.findAll().stream().map(assembler::toDomain).toList();
    }

    public Optional<OperationalAlert> findById(String id) {
        return repository.findById(id).map(assembler::toDomain);
    }

    public List<OperationalAlert> findActive() {
        return repository.findByStatusIn(List.of(AlertStatus.PENDING, AlertStatus.IN_PROGRESS)).stream().map(assembler::toDomain).toList();
    }

    public OperationalAlert save(OperationalAlert alert) {
        return assembler.toDomain(repository.save(assembler.toEntity(alert)));
    }

    public boolean existsById(String id) {
        return repository.existsById(id);
    }
}
