package com.retailpulse.platform.trafficanalytics.domain.model.commands;

public record CreateHeatmapMetricCommand(String id, String zoneId, Integer traffic, Integer averageDwellTimeSeconds, Double conversionRate, Integer intensity, Boolean attentionRequired) {
}
