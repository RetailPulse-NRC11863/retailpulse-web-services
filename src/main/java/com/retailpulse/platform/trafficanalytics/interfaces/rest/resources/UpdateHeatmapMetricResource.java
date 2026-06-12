package com.retailpulse.platform.trafficanalytics.interfaces.rest.resources;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateHeatmapMetricResource(
    @NotBlank String zoneId,
    @NotNull @PositiveOrZero Integer traffic,
    @NotNull @PositiveOrZero Integer averageDwellTimeSeconds,
    @NotNull @Min(0) @Max(100) Double conversionRate,
    @NotNull @Min(0) @Max(100) Integer intensity,
    @NotNull Boolean attentionRequired
) {
}
