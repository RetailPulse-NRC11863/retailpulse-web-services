package com.retailpulse.platform.storeoperations.infrastructure.persistence.jpa.assemblers;

import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalAlert;
import com.retailpulse.platform.storeoperations.infrastructure.persistence.jpa.entities.OperationalAlertPersistenceEntity;

public class OperationalAlertPersistenceAssembler {
    public OperationalAlert toDomain(OperationalAlertPersistenceEntity entity) {
        return new OperationalAlert(
            entity.getId(),
            entity.getType(),
            entity.getPriority(),
            entity.getStatus(),
            entity.getMessage(),
            entity.getZoneId(),
            entity.getZoneName(),
            entity.getProductId(),
            entity.getProductName(),
            entity.getCreatedAt()
        );
    }

    public OperationalAlertPersistenceEntity toEntity(OperationalAlert alert) {
        OperationalAlertPersistenceEntity entity = new OperationalAlertPersistenceEntity();
        entity.setId(alert.getId());
        entity.setType(alert.getType());
        entity.setPriority(alert.getPriority());
        entity.setStatus(alert.getStatus());
        entity.setMessage(alert.getMessage());
        entity.setZoneId(alert.getZoneId());
        entity.setZoneName(alert.getZoneName());
        entity.setProductId(alert.getProductId());
        entity.setProductName(alert.getProductName());
        entity.setCreatedAt(alert.getCreatedAt());
        return entity;
    }
}
