package com.retailpulse.platform.storeoperations.infrastructure.persistence.jpa.repositories;

import com.retailpulse.platform.storeoperations.infrastructure.persistence.jpa.entities.OperationalAlertPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertStatus;

public interface OperationalAlertPersistenceRepository extends JpaRepository<OperationalAlertPersistenceEntity, Long> {

    List<OperationalAlertPersistenceEntity> findByStatus(AlertStatus status);

}
