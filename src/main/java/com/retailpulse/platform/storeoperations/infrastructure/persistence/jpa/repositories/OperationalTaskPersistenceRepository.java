package com.retailpulse.platform.storeoperations.infrastructure.persistence.jpa.repositories;

import com.retailpulse.platform.storeoperations.domain.model.valueobjects.TaskStatus;
import com.retailpulse.platform.storeoperations.infrastructure.persistence.jpa.entities.OperationalTaskPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OperationalTaskPersistenceRepository extends JpaRepository<OperationalTaskPersistenceEntity, String> {
    List<OperationalTaskPersistenceEntity> findByStatusIn(List<TaskStatus> statuses);
}
