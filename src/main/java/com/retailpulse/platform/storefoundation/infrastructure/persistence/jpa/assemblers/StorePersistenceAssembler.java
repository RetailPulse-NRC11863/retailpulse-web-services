package com.retailpulse.platform.storefoundation.infrastructure.persistence.jpa.assemblers;

import com.retailpulse.platform.storefoundation.domain.model.aggregates.Store;
import com.retailpulse.platform.storefoundation.infrastructure.persistence.jpa.entities.StorePersistenceEntity;

public class StorePersistenceAssembler {
    public Store toDomain(StorePersistenceEntity entity) {
        return new Store(entity.getId(), entity.getName(), entity.getAddress(), entity.getManagerName(), entity.getStatus());
    }
    public StorePersistenceEntity toEntity(Store store) {
        StorePersistenceEntity entity = new StorePersistenceEntity();
        entity.setId(store.getId());
        entity.setName(store.getName());
        entity.setAddress(store.getAddress());
        entity.setManagerName(store.getManagerName());
        entity.setStatus(store.getStatus());
        return entity;
    }
}
