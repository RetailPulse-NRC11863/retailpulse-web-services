package com.retailpulse.platform.trafficanalytics.domain.model.aggregates;

import com.retailpulse.platform.trafficanalytics.domain.model.valueobjects.HeatLevel;

public class HeatmapMetric {
    private String id;
    private String zoneId;
    private Integer traffic;
    private Integer averageDwellTimeSeconds;
    private Double conversionRate;
    private Integer intensity;
    private Boolean attentionRequired;

    public HeatmapMetric() {
    }

    public HeatmapMetric(String id, String zoneId, Integer traffic, Integer averageDwellTimeSeconds, Double conversionRate, Integer intensity, Boolean attentionRequired) {
        validateId(id);
        validateZoneId(zoneId);
        validateTraffic(traffic);
        validateAverageDwellTimeSeconds(averageDwellTimeSeconds);
        validateConversionRate(conversionRate);
        validateIntensity(intensity);
        this.id = id;
        this.zoneId = zoneId;
        this.traffic = traffic;
        this.averageDwellTimeSeconds = averageDwellTimeSeconds;
        this.conversionRate = conversionRate;
        this.intensity = intensity;
        this.attentionRequired = attentionRequired != null && attentionRequired;
    }

    public void update(String zoneId, Integer traffic, Integer averageDwellTimeSeconds, Double conversionRate, Integer intensity, Boolean attentionRequired) {
        validateZoneId(zoneId);
        validateTraffic(traffic);
        validateAverageDwellTimeSeconds(averageDwellTimeSeconds);
        validateConversionRate(conversionRate);
        validateIntensity(intensity);
        this.zoneId = zoneId;
        this.traffic = traffic;
        this.averageDwellTimeSeconds = averageDwellTimeSeconds;
        this.conversionRate = conversionRate;
        this.intensity = intensity;
        this.attentionRequired = attentionRequired != null && attentionRequired;
    }

    public HeatLevel heatLevel() {
        if (intensity >= 90) return HeatLevel.CRITICAL;
        if (intensity >= 70) return HeatLevel.HIGH;
        if (intensity >= 40) return HeatLevel.MEDIUM;
        return HeatLevel.LOW;
    }

    private void validateId(String id) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Heatmap metric id is required");
    }

    private void validateZoneId(String zoneId) {
        if (zoneId == null || zoneId.isBlank()) throw new IllegalArgumentException("Heatmap metric zone id is required");
    }

    private void validateTraffic(Integer traffic) {
        if (traffic == null || traffic < 0) throw new IllegalArgumentException("Traffic must be zero or positive");
    }

    private void validateAverageDwellTimeSeconds(Integer averageDwellTimeSeconds) {
        if (averageDwellTimeSeconds == null || averageDwellTimeSeconds < 0) throw new IllegalArgumentException("Average dwell time must be zero or positive");
    }

    private void validateConversionRate(Double conversionRate) {
        if (conversionRate == null || conversionRate < 0 || conversionRate > 100) throw new IllegalArgumentException("Conversion rate must be between 0 and 100");
    }

    private void validateIntensity(Integer intensity) {
        if (intensity == null || intensity < 0 || intensity > 100) throw new IllegalArgumentException("Intensity must be between 0 and 100");
    }

    public String getId() {
        return id;
    }

    public String getZoneId() {
        return zoneId;
    }

    public Integer getTraffic() {
        return traffic;
    }

    public Integer getAverageDwellTimeSeconds() {
        return averageDwellTimeSeconds;
    }

    public Double getConversionRate() {
        return conversionRate;
    }

    public Integer getIntensity() {
        return intensity;
    }

    public Boolean getAttentionRequired() {
        return attentionRequired;
    }
}
