package com.retailpulse.platform.inventoryintelligence.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateInventoryItemResource(@NotNull Long productId, @PositiveOrZero Integer availableStock, @PositiveOrZero Integer criticalThreshold) {
}
