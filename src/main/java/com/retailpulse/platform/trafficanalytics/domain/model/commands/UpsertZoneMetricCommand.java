package com.retailpulse.platform.trafficanalytics.domain.model.commands;

public record UpsertZoneMetricCommand(Long zoneId, Integer trafficCount, Double averageDwellTime, Integer interactionCount, Double conversionRate, Integer intensity) {
}
