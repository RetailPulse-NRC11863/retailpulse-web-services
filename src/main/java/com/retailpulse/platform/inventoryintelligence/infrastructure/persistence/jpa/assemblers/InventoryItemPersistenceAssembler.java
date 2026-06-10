package com.retailpulse.platform.inventoryintelligence.infrastructure.persistence.jpa.assemblers;

import com.retailpulse.platform.inventoryintelligence.domain.model.aggregates.InventoryItem;
import com.retailpulse.platform.inventoryintelligence.infrastructure.persistence.jpa.entities.InventoryItemPersistenceEntity;

public class InventoryItemPersistenceAssembler {
    public InventoryItem toDomain(InventoryItemPersistenceEntity entity) { return new InventoryItem(entity.getId(), entity.getProductId(), entity.getAvailableStock(), entity.getCriticalThreshold(), entity.getStatus()); }
    public InventoryItemPersistenceEntity toEntity(InventoryItem item) {
        InventoryItemPersistenceEntity entity = new InventoryItemPersistenceEntity();
        entity.setId(item.getId());
        entity.setProductId(item.getProductId());
        entity.setAvailableStock(item.getAvailableStock());
        entity.setCriticalThreshold(item.getCriticalThreshold());
        entity.setStatus(item.getStatus());
        return entity;
    }
}
