package com.retailpulse.platform.trafficanalytics.interfaces.rest.resources;

import com.retailpulse.platform.trafficanalytics.domain.model.valueobjects.HeatLevel;

public record HeatmapMetricResource(Long zoneId, Integer trafficCount, Double averageDwellTime, Double conversionRate, HeatLevel heatLevel) {
}
