package com.retailpulse.platform.storefoundation.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record CreateProductResource(@NotNull Long storeId, @NotBlank String name, @NotBlank String sku, @NotBlank String category, String description, @PositiveOrZero BigDecimal price, Long zoneId, String aisle, String shelf, String displayReference) {
}
