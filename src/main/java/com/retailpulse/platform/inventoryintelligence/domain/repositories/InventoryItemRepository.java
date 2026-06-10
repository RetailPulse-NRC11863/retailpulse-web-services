package com.retailpulse.platform.inventoryintelligence.domain.repositories;

import com.retailpulse.platform.inventoryintelligence.domain.model.aggregates.InventoryItem;
import java.util.List;
import java.util.Optional;

public interface InventoryItemRepository {
    InventoryItem save(InventoryItem item);
    List<InventoryItem> findAll();
    Optional<InventoryItem> findByProductId(Long productId);
    List<InventoryItem> findCriticalItems();
    long count();
}
