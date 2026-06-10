package com.retailpulse.platform.storefoundation.domain.model.commands;

import java.math.BigDecimal;
import com.retailpulse.platform.storefoundation.domain.model.valueobjects.ProductLocation;

public record CreateProductCommand(Long storeId, String name, String sku, String category, String description, BigDecimal price, Long zoneId, ProductLocation location) {
}
