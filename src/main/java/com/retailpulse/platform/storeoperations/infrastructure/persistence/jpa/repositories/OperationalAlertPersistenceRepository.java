package com.retailpulse.platform.storeoperations.infrastructure.persistence.jpa.repositories;

import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertStatus;
import com.retailpulse.platform.storeoperations.infrastructure.persistence.jpa.entities.OperationalAlertPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OperationalAlertPersistenceRepository extends JpaRepository<OperationalAlertPersistenceEntity, String> {
    List<OperationalAlertPersistenceEntity> findByStatusIn(List<AlertStatus> statuses);
}
