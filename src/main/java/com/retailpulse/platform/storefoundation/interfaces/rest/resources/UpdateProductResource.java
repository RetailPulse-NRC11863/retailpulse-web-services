package com.retailpulse.platform.storefoundation.interfaces.rest.resources;

import com.retailpulse.platform.storefoundation.domain.model.valueobjects.ProductStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record UpdateProductResource(@NotBlank String name, @NotBlank String category, String description, @PositiveOrZero BigDecimal price, ProductStatus status, Long zoneId, String aisle, String shelf, String displayReference) {
}
