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
    public OperationalTaskRepositoryImpl(OperationalTaskPersistenceRepository repository) { this.repository = repository; }
    public OperationalTask save(OperationalTask task) { return assembler.toDomain(repository.save(assembler.toEntity(task))); }
    public List<OperationalTask> findAll() { return repository.findAll().stream().map(assembler::toDomain).toList(); }
    public List<OperationalTask> findPending() { return repository.findByStatus(TaskStatus.PENDING).stream().map(assembler::toDomain).toList(); }
    public Optional<OperationalTask> findById(Long taskId) { return repository.findById(taskId).map(assembler::toDomain); }
    public long count() { return repository.count(); }
}
