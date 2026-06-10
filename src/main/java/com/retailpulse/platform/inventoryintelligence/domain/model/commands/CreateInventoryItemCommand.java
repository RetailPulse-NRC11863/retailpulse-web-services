package com.retailpulse.platform.inventoryintelligence.domain.model.commands;

public record CreateInventoryItemCommand(Long productId, Integer availableStock, Integer criticalThreshold) {
}
