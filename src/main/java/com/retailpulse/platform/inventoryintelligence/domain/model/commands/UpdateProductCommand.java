package com.retailpulse.platform.inventoryintelligence.domain.model.commands;

import java.math.BigDecimal;

public record UpdateProductCommand(String id, String name, String category, BigDecimal price, Integer stock, String zoneName, String shelfReference, String promotion) {
}
