package com.retailpulse.platform.storeoperations.infrastructure.persistence.jpa.repositories;

import com.retailpulse.platform.storeoperations.infrastructure.persistence.jpa.entities.OperationalTaskPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.TaskStatus;

public interface OperationalTaskPersistenceRepository extends JpaRepository<OperationalTaskPersistenceEntity, Long> {

    List<OperationalTaskPersistenceEntity> findByStatus(TaskStatus status);

}
