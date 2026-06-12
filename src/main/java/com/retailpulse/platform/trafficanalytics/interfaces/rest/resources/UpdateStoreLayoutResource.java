package com.retailpulse.platform.trafficanalytics.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateStoreLayoutResource(
    @NotBlank String name,
    @NotBlank String storeName,
    @NotNull @Positive Integer width,
    @NotNull @Positive Integer height,
    @NotNull Boolean active
) {
}
