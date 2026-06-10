package com.retailpulse.platform.storeoperations.infrastructure.persistence.jpa.assemblers;

import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalTask;
import com.retailpulse.platform.storeoperations.infrastructure.persistence.jpa.entities.OperationalTaskPersistenceEntity;

public class OperationalTaskPersistenceAssembler {
    public OperationalTask toDomain(OperationalTaskPersistenceEntity e) { return new OperationalTask(e.getId(), e.getStoreId(), e.getTitle(), e.getDescription(), e.getStatus(), e.getPriority()); }
    public OperationalTaskPersistenceEntity toEntity(OperationalTask t) { OperationalTaskPersistenceEntity e = new OperationalTaskPersistenceEntity(); e.setId(t.getId()); e.setStoreId(t.getStoreId()); e.setTitle(t.getTitle()); e.setDescription(t.getDescription()); e.setStatus(t.getStatus()); e.setPriority(t.getPriority()); return e; }
}
