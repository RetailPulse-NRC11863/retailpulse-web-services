package com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.assemblers;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.ZoneMetric;
import com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.entities.ZoneMetricPersistenceEntity;

public class ZoneMetricPersistenceAssembler {
    public ZoneMetric toDomain(ZoneMetricPersistenceEntity e) { return new ZoneMetric(e.getId(), e.getZoneId(), e.getTrafficCount(), e.getAverageDwellTime(), e.getInteractionCount(), e.getConversionRate(), e.getHeatLevel(), e.getCongestionStatus()); }
    public ZoneMetricPersistenceEntity toEntity(ZoneMetric m) { ZoneMetricPersistenceEntity e = new ZoneMetricPersistenceEntity(); e.setId(m.getId()); e.setZoneId(m.getZoneId()); e.setTrafficCount(m.getTrafficCount()); e.setAverageDwellTime(m.getAverageDwellTime()); e.setInteractionCount(m.getInteractionCount()); e.setConversionRate(m.getConversionRate()); e.setHeatLevel(m.getHeatLevel()); e.setCongestionStatus(m.getCongestionStatus()); return e; }
}
