package com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.assemblers;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.HeatmapMetric;
import com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.entities.HeatmapMetricPersistenceEntity;

public class HeatmapMetricPersistenceAssembler {
    public static HeatmapMetric toDomainFromPersistence(HeatmapMetricPersistenceEntity entity) {
        return new HeatmapMetric(
            entity.getId(),
            entity.getZoneId(),
            entity.getTraffic(),
            entity.getAverageDwellTimeSeconds(),
            entity.getConversionRate(),
            entity.getIntensity(),
            entity.getAttentionRequired()
        );
    }

    public static HeatmapMetricPersistenceEntity toPersistenceFromDomain(HeatmapMetric heatmapMetric) {
        HeatmapMetricPersistenceEntity entity = new HeatmapMetricPersistenceEntity();
        entity.setId(heatmapMetric.getId());
        entity.setZoneId(heatmapMetric.getZoneId());
        entity.setTraffic(heatmapMetric.getTraffic());
        entity.setAverageDwellTimeSeconds(heatmapMetric.getAverageDwellTimeSeconds());
        entity.setConversionRate(heatmapMetric.getConversionRate());
        entity.setIntensity(heatmapMetric.getIntensity());
        entity.setAttentionRequired(heatmapMetric.getAttentionRequired());
        return entity;
    }
}
