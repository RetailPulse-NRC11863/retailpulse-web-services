package com.retailpulse.platform.inventoryintelligence.domain.model.valueobjects;

public record ProductId(String value) {
    public ProductId {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("Product id is required");
    }
}
