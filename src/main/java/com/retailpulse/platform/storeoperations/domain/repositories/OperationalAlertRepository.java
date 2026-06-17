package com.retailpulse.platform.storeoperations.domain.repositories;

import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalAlert;
import java.util.List;
import java.util.Optional;

public interface OperationalAlertRepository {
    List<OperationalAlert> findAll();
    Optional<OperationalAlert> findById(String id);
    List<OperationalAlert> findActive();
    OperationalAlert save(OperationalAlert alert);
    boolean existsById(String id);
}
