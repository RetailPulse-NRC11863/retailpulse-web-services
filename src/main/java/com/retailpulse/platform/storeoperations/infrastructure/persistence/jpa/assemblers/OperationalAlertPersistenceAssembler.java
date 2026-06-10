package com.retailpulse.platform.storeoperations.infrastructure.persistence.jpa.assemblers;

import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalAlert;
import com.retailpulse.platform.storeoperations.infrastructure.persistence.jpa.entities.OperationalAlertPersistenceEntity;

public class OperationalAlertPersistenceAssembler {
    public OperationalAlert toDomain(OperationalAlertPersistenceEntity e) { return new OperationalAlert(e.getId(), e.getStoreId(), e.getTitle(), e.getDescription(), e.getType(), e.getStatus(), e.getPriority()); }
    public OperationalAlertPersistenceEntity toEntity(OperationalAlert a) { OperationalAlertPersistenceEntity e = new OperationalAlertPersistenceEntity(); e.setId(a.getId()); e.setStoreId(a.getStoreId()); e.setTitle(a.getTitle()); e.setDescription(a.getDescription()); e.setType(a.getType()); e.setStatus(a.getStatus()); e.setPriority(a.getPriority()); return e; }
}
