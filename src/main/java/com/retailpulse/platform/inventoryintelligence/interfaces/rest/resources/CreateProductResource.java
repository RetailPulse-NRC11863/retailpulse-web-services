package com.retailpulse.platform.inventoryintelligence.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record CreateProductResource(
    @NotBlank String id,
    @NotBlank String name,
    @NotBlank String category,
    @NotNull @PositiveOrZero BigDecimal price,
    @NotNull @PositiveOrZero Integer stock,
    @NotBlank String zoneName,
    @NotBlank String shelfReference,
    String promotion
) {
}
