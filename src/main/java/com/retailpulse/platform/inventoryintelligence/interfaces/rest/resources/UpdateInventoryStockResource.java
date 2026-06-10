package com.retailpulse.platform.inventoryintelligence.interfaces.rest.resources;

import jakarta.validation.constraints.PositiveOrZero;

public record UpdateInventoryStockResource(@PositiveOrZero Integer availableStock) {
}
