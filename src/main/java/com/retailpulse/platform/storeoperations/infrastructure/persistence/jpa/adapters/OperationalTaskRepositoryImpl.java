package com.retailpulse.platform.storeoperations.infrastructure.persistence.jpa.adapters;

import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalTask;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.TaskStatus;
import com.retailpulse.platform.storeoperations.domain.repositories.OperationalTaskRepository;
import com.retailpulse.platform.storeoperations.infrastructure.persistence.jpa.assemblers.OperationalTaskPersistenceAssembler;
import com.retailpulse.platform.storeoperations.infrastructure.persistence.jpa.repositories.OperationalTaskPersistenceRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class OperationalTaskRepositoryImpl implements OperationalTaskRepository {
    private final OperationalTaskPersistenceRepository repository;
    private final OperationalTaskPersistenceAssembler assembler = new OperationalTaskPersistenceAssembler();

    public OperationalTaskRepositoryImpl(OperationalTaskPersistenceRepository repository) {
        this.repository = repository;
    }

    public List<OperationalTask> findAll() {
        return repository.findAll().stream().map(assembler::toDomain).toList();
    }

    public Optional<OperationalTask> findById(String id) {
        return repository.findById(id).map(assembler::toDomain);
    }

    public List<OperationalTask> findPending() {
        return repository.findByStatusIn(List.of(TaskStatus.PENDING, TaskStatus.IN_PROGRESS)).stream().map(assembler::toDomain).toList();
    }

    public OperationalTask save(OperationalTask task) {
        return assembler.toDomain(repository.save(assembler.toEntity(task)));
    }

    public boolean existsById(String id) {
        return repository.existsById(id);
    }
}
