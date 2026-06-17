package com.retailpulse.platform.storeoperations.domain.repositories;

import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalTask;
import java.util.List;
import java.util.Optional;

public interface OperationalTaskRepository {
    List<OperationalTask> findAll();
    Optional<OperationalTask> findById(String id);
    List<OperationalTask> findPending();
    OperationalTask save(OperationalTask task);
    boolean existsById(String id);
}
