package com.retailpulse.platform.storefoundation.interfaces.rest.resources;

import com.retailpulse.platform.storefoundation.domain.model.valueobjects.ProductStatus;
import java.math.BigDecimal;

public record ProductResource(Long id, Long storeId, String name, String sku, String category, String description, BigDecimal price, ProductStatus status, Long zoneId, String aisle, String shelf, String displayReference) {
}
