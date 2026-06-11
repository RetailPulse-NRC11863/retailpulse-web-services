package com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "heatmap_metrics")
public class HeatmapMetricPersistenceEntity {
    @Id
    private String id;

    @Column(nullable = false)
    private String zoneId;

    @Column(nullable = false)
    private Integer traffic;

    @Column(nullable = false)
    private Integer averageDwellTimeSeconds;

    @Column(nullable = false)
    private Double conversionRate;

    @Column(nullable = false)
    private Integer intensity;

    @Column(nullable = false)
    private Boolean attentionRequired;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = OffsetDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = OffsetDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public Integer getTraffic() {
        return traffic;
    }

    public void setTraffic(Integer traffic) {
        this.traffic = traffic;
    }

    public Integer getAverageDwellTimeSeconds() {
        return averageDwellTimeSeconds;
    }

    public void setAverageDwellTimeSeconds(Integer averageDwellTimeSeconds) {
        this.averageDwellTimeSeconds = averageDwellTimeSeconds;
    }

    public Double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public Integer getIntensity() {
        return intensity;
    }

    public void setIntensity(Integer intensity) {
        this.intensity = intensity;
    }

    public Boolean getAttentionRequired() {
        return attentionRequired;
    }

    public void setAttentionRequired(Boolean attentionRequired) {
        this.attentionRequired = attentionRequired;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
