package com.retailpulse.platform.trafficanalytics.interfaces.rest.resources;

import com.retailpulse.platform.trafficanalytics.domain.model.valueobjects.HeatLevel;
import com.retailpulse.platform.trafficanalytics.domain.model.valueobjects.CongestionStatus;

public record ZoneMetricResource(Long zoneId, Integer trafficCount, Double averageDwellTime, Integer interactionCount, Double conversionRate, HeatLevel heatLevel, CongestionStatus congestionStatus) {
}
