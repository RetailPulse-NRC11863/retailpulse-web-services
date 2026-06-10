package com.retailpulse.platform.assistedshopping.infrastructure.persistence.jpa.assemblers;

import com.retailpulse.platform.assistedshopping.domain.model.aggregates.KioskSession;
import com.retailpulse.platform.assistedshopping.infrastructure.persistence.jpa.entities.KioskSessionPersistenceEntity;

public class KioskSessionPersistenceAssembler {
    public KioskSession toDomain(KioskSessionPersistenceEntity entity) { return new KioskSession(entity.getId(), entity.getStoreId(), entity.getStatus()); }
    public KioskSessionPersistenceEntity toEntity(KioskSession session) { KioskSessionPersistenceEntity entity = new KioskSessionPersistenceEntity(); entity.setId(session.getId()); entity.setStoreId(session.getStoreId()); entity.setStatus(session.getStatus()); return entity; }
}
