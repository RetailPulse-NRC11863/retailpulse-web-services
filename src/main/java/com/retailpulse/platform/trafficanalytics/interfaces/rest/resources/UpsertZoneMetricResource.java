package com.retailpulse.platform.trafficanalytics.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record UpsertZoneMetricResource(@PositiveOrZero Integer trafficCount, @PositiveOrZero Double averageDwellTime, @PositiveOrZero Integer interactionCount, @PositiveOrZero Double conversionRate, @NotNull @PositiveOrZero Integer intensity) {
}
