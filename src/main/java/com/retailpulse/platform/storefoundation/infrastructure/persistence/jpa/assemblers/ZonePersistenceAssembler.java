package com.retailpulse.platform.storefoundation.infrastructure.persistence.jpa.assemblers;

import com.retailpulse.platform.storefoundation.domain.model.entities.Zone;
import com.retailpulse.platform.storefoundation.infrastructure.persistence.jpa.entities.ZonePersistenceEntity;

public class ZonePersistenceAssembler {
    public Zone toDomain(ZonePersistenceEntity entity) {
        return new Zone(entity.getId(), entity.getStoreId(), entity.getName(), entity.getType(), entity.getCapacity(), entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
    }
    public ZonePersistenceEntity toEntity(Zone zone) {
        ZonePersistenceEntity entity = new ZonePersistenceEntity();
        entity.setId(zone.getId());
        entity.setStoreId(zone.getStoreId());
        entity.setName(zone.getName());
        entity.setType(zone.getType());
        entity.setCapacity(zone.getCapacity());
        entity.setX(zone.getX());
        entity.setY(zone.getY());
        entity.setWidth(zone.getWidth());
        entity.setHeight(zone.getHeight());
        return entity;
    }
}
