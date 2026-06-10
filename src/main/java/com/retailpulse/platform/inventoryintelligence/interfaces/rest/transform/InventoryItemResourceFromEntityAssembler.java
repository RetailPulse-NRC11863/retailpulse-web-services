package com.retailpulse.platform.inventoryintelligence.interfaces.rest.transform;

import com.retailpulse.platform.inventoryintelligence.domain.model.aggregates.InventoryItem;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.resources.InventoryItemResource;

public class InventoryItemResourceFromEntityAssembler {
    public static InventoryItemResource toResource(InventoryItem item) { return new InventoryItemResource(item.getId(), item.getProductId(), item.getAvailableStock(), item.getCriticalThreshold(), item.getStatus()); }
}
