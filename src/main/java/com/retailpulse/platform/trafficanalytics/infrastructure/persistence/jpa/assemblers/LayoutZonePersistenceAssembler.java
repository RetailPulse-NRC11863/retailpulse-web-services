package com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.assemblers;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.LayoutZone;
import com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.entities.LayoutZonePersistenceEntity;

public class LayoutZonePersistenceAssembler {
    public static LayoutZone toDomainFromPersistence(LayoutZonePersistenceEntity entity) {
        return new LayoutZone(entity.getId(), entity.getName(), entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight(), entity.getType());
    }

    public static LayoutZonePersistenceEntity toPersistenceFromDomain(LayoutZone layoutZone) {
        LayoutZonePersistenceEntity entity = new LayoutZonePersistenceEntity();
        entity.setId(layoutZone.getId());
        entity.setName(layoutZone.getName());
        entity.setX(layoutZone.getX());
        entity.setY(layoutZone.getY());
        entity.setWidth(layoutZone.getWidth());
        entity.setHeight(layoutZone.getHeight());
        entity.setType(layoutZone.getType());
        return entity;
    }
}
