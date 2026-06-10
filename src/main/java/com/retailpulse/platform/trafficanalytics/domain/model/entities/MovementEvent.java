package com.retailpulse.platform.trafficanalytics.domain.model.entities;

import com.retailpulse.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import java.time.OffsetDateTime;

public class MovementEvent extends AuditableAbstractAggregateRoot {
    private Long zoneId;
    private String eventType;
    private OffsetDateTime occurredAt;

    public MovementEvent() {
    }

    public MovementEvent(Long id, Long zoneId, String eventType, OffsetDateTime occurredAt) {
        setId(id);
        this.zoneId = zoneId;
        this.eventType = eventType;
        this.occurredAt = occurredAt;
    }

    public Long getZoneId() {
        return zoneId;
    }

    public void setZoneId(Long zoneId) {
        this.zoneId = zoneId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public OffsetDateTime getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(OffsetDateTime occurredAt) {
        this.occurredAt = occurredAt;
    }


}
