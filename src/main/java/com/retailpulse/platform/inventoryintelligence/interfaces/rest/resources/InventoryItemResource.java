package com.retailpulse.platform.inventoryintelligence.interfaces.rest.resources;

import com.retailpulse.platform.inventoryintelligence.domain.model.valueobjects.StockStatus;

public record InventoryItemResource(Long id, Long productId, Integer availableStock, Integer criticalThreshold, StockStatus status) {
}
