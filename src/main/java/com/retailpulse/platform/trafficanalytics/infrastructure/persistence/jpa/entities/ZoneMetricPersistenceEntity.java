package com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.entities;

import com.retailpulse.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import com.retailpulse.platform.trafficanalytics.domain.model.valueobjects.HeatLevel;
import com.retailpulse.platform.trafficanalytics.domain.model.valueobjects.CongestionStatus;

@Entity
@Table(name = "zone_metrics")
public class ZoneMetricPersistenceEntity extends AuditableAbstractPersistenceEntity {
    private Long zoneId;

    private Integer trafficCount;

    private Double averageDwellTime;

    private Integer interactionCount;

    private Double conversionRate;

    @Enumerated(EnumType.STRING)
    private HeatLevel heatLevel;

    @Enumerated(EnumType.STRING)
    private CongestionStatus congestionStatus;

    public ZoneMetricPersistenceEntity() {
    }

    public Long getZoneId() {
        return zoneId;
    }

    public void setZoneId(Long zoneId) {
        this.zoneId = zoneId;
    }

    public Integer getTrafficCount() {
        return trafficCount;
    }

    public void setTrafficCount(Integer trafficCount) {
        this.trafficCount = trafficCount;
    }

    public Double getAverageDwellTime() {
        return averageDwellTime;
    }

    public void setAverageDwellTime(Double averageDwellTime) {
        this.averageDwellTime = averageDwellTime;
    }

    public Integer getInteractionCount() {
        return interactionCount;
    }

    public void setInteractionCount(Integer interactionCount) {
        this.interactionCount = interactionCount;
    }

    public Double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public HeatLevel getHeatLevel() {
        return heatLevel;
    }

    public void setHeatLevel(HeatLevel heatLevel) {
        this.heatLevel = heatLevel;
    }

    public CongestionStatus getCongestionStatus() {
        return congestionStatus;
    }

    public void setCongestionStatus(CongestionStatus congestionStatus) {
        this.congestionStatus = congestionStatus;
    }


}
