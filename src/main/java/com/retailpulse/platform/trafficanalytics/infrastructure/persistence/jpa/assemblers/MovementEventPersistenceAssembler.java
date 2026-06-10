package com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.assemblers;

import com.retailpulse.platform.trafficanalytics.domain.model.entities.MovementEvent;
import com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.entities.MovementEventPersistenceEntity;

public class MovementEventPersistenceAssembler {
    public MovementEvent toDomain(MovementEventPersistenceEntity e) { return new MovementEvent(e.getId(), e.getZoneId(), e.getEventType(), e.getOccurredAt()); }
    public MovementEventPersistenceEntity toEntity(MovementEvent m) { MovementEventPersistenceEntity e = new MovementEventPersistenceEntity(); e.setId(m.getId()); e.setZoneId(m.getZoneId()); e.setEventType(m.getEventType()); e.setOccurredAt(m.getOccurredAt()); return e; }
}
