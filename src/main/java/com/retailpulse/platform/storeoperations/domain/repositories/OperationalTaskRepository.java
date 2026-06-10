package com.retailpulse.platform.storeoperations.domain.repositories;

import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalTask;
import java.util.List;
import java.util.Optional;

public interface OperationalTaskRepository {
    OperationalTask save(OperationalTask task);
    List<OperationalTask> findAll();
    List<OperationalTask> findPending();
    Optional<OperationalTask> findById(Long taskId);
    long count();
}
