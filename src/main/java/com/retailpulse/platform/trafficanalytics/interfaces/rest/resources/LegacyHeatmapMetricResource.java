package com.retailpulse.platform.trafficanalytics.interfaces.rest.resources;

import com.retailpulse.platform.trafficanalytics.domain.model.valueobjects.HeatLevel;

public record LegacyHeatmapMetricResource(Long zoneId, Integer trafficCount, Double conversionRate, HeatLevel heatLevel) {
}
