package com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.assemblers;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.StoreLayout;
import com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.entities.StoreLayoutPersistenceEntity;

public class StoreLayoutPersistenceAssembler {
    public static StoreLayout toDomainFromPersistence(StoreLayoutPersistenceEntity entity) {
        return new StoreLayout(entity.getId(), entity.getName(), entity.getStoreName(), entity.getWidth(), entity.getHeight(), entity.getActive());
    }

    public static StoreLayoutPersistenceEntity toPersistenceFromDomain(StoreLayout storeLayout) {
        StoreLayoutPersistenceEntity entity = new StoreLayoutPersistenceEntity();
        entity.setId(storeLayout.getId());
        entity.setName(storeLayout.getName());
        entity.setStoreName(storeLayout.getStoreName());
        entity.setWidth(storeLayout.getWidth());
        entity.setHeight(storeLayout.getHeight());
        entity.setActive(storeLayout.getActive());
        return entity;
    }
}
