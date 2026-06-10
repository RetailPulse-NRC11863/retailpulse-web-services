package com.retailpulse.platform.trafficanalytics.domain.model.aggregates;

import com.retailpulse.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.retailpulse.platform.trafficanalytics.domain.model.valueobjects.HeatLevel;
import com.retailpulse.platform.trafficanalytics.domain.model.valueobjects.CongestionStatus;

public class ZoneMetric extends AuditableAbstractAggregateRoot {
    private Long zoneId;
    private Integer trafficCount;
    private Double averageDwellTime;
    private Integer interactionCount;
    private Double conversionRate;
    private HeatLevel heatLevel;
    private CongestionStatus congestionStatus;

    public ZoneMetric() {
    }

    public ZoneMetric(Long id, Long zoneId, Integer trafficCount, Double averageDwellTime, Integer interactionCount, Double conversionRate, HeatLevel heatLevel, CongestionStatus congestionStatus) {
        setId(id);
        this.zoneId = zoneId;
        this.trafficCount = trafficCount;
        this.averageDwellTime = averageDwellTime;
        this.interactionCount = interactionCount;
        this.conversionRate = conversionRate;
        this.heatLevel = heatLevel;
        this.congestionStatus = congestionStatus;
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
