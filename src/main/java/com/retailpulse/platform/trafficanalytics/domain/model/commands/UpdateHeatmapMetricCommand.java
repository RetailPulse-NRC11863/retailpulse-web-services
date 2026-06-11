package com.retailpulse.platform.trafficanalytics.domain.model.commands;

public record UpdateHeatmapMetricCommand(String id, String zoneId, Integer traffic, Integer averageDwellTimeSeconds, Double conversionRate, Integer intensity, Boolean attentionRequired) {
}
