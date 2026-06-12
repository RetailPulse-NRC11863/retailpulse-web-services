package com.retailpulse.platform.trafficanalytics.interfaces.rest.resources;

public record HeatmapMetricResource(String id, String zoneId, Integer traffic, Integer averageDwellTimeSeconds, Double conversionRate, Integer intensity, Boolean attentionRequired) {
}
