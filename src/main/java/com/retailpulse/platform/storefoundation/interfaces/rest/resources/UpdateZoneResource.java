package com.retailpulse.platform.storefoundation.interfaces.rest.resources;

import com.retailpulse.platform.storefoundation.domain.model.valueobjects.ZoneType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateZoneResource(@NotBlank String name, @NotNull ZoneType type, @PositiveOrZero Integer capacity) {
}
