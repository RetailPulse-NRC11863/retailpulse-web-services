package com.retailpulse.platform.storeoperations.domain.repositories;

import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalAlert;
import java.util.List;
import java.util.Optional;

public interface OperationalAlertRepository {
    OperationalAlert save(OperationalAlert alert);
    List<OperationalAlert> findAll();
    List<OperationalAlert> findActive();
    Optional<OperationalAlert> findById(Long alertId);
    long count();
}
