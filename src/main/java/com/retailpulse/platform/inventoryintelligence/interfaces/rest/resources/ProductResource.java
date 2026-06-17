package com.retailpulse.platform.inventoryintelligence.interfaces.rest.resources;

import java.math.BigDecimal;

public record ProductResource(String id, String name, String category, BigDecimal price, Integer stock, String zoneName, String shelfReference, String promotion) {
}
