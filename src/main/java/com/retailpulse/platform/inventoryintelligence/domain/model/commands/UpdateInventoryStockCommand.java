package com.retailpulse.platform.inventoryintelligence.domain.model.commands;

public record UpdateInventoryStockCommand(Long productId, Integer availableStock) {
}
