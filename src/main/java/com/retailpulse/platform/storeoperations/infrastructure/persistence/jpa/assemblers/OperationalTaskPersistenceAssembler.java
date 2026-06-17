package com.retailpulse.platform.storeoperations.infrastructure.persistence.jpa.assemblers;

import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalTask;
import com.retailpulse.platform.storeoperations.infrastructure.persistence.jpa.entities.OperationalTaskPersistenceEntity;

public class OperationalTaskPersistenceAssembler {
    public OperationalTask toDomain(OperationalTaskPersistenceEntity entity) {
        return new OperationalTask(
            entity.getId(),
            entity.getTitle(),
            entity.getDescription(),
            entity.getPriority(),
            entity.getStatus(),
            entity.getZoneId(),
            entity.getZoneName(),
            entity.getAlertId(),
            entity.getCreatedAt()
        );
    }

    public OperationalTaskPersistenceEntity toEntity(OperationalTask task) {
        OperationalTaskPersistenceEntity entity = new OperationalTaskPersistenceEntity();
        entity.setId(task.getId());
        entity.setTitle(task.getTitle());
        entity.setDescription(task.getDescription());
        entity.setPriority(task.getPriority());
        entity.setStatus(task.getStatus());
        entity.setZoneId(task.getZoneId());
        entity.setZoneName(task.getZoneName());
        entity.setAlertId(task.getAlertId());
        entity.setCreatedAt(task.getCreatedAt());
        return entity;
    }
}
