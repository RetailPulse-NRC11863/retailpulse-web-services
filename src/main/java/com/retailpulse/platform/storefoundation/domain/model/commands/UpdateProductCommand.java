package com.retailpulse.platform.storefoundation.domain.model.commands;

import java.math.BigDecimal;
import com.retailpulse.platform.storefoundation.domain.model.valueobjects.ProductLocation;
import com.retailpulse.platform.storefoundation.domain.model.valueobjects.ProductStatus;

public record UpdateProductCommand(Long productId, String name, String category, String description, BigDecimal price, ProductStatus status, Long zoneId, ProductLocation location) {
}
