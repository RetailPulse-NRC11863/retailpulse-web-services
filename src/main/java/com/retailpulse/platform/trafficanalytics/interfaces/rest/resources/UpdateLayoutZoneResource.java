package com.retailpulse.platform.trafficanalytics.interfaces.rest.resources;

import com.retailpulse.platform.trafficanalytics.domain.model.valueobjects.ZoneType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateLayoutZoneResource(
    @NotBlank String name,
    @NotNull @PositiveOrZero Integer x,
    @NotNull @PositiveOrZero Integer y,
    @NotNull @Positive Integer width,
    @NotNull @Positive Integer height,
    @NotNull ZoneType type
) {
}
