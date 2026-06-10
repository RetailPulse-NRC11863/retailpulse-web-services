package com.retailpulse.platform.inventoryintelligence.application.queryservices;

import com.retailpulse.platform.inventoryintelligence.domain.model.aggregates.InventoryItem;
import java.util.List;
import java.util.Optional;

public interface InventoryItemQueryService {
    List<InventoryItem> handleGetAll();
    Optional<InventoryItem> handleGetByProductId(Long productId);
    List<InventoryItem> handleGetCritical();
}
