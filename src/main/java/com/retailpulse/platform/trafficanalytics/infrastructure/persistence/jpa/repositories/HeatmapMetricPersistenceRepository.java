package com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.repositories;

import com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.entities.HeatmapMetricPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HeatmapMetricPersistenceRepository extends JpaRepository<HeatmapMetricPersistenceEntity, String> {
    List<HeatmapMetricPersistenceEntity> findByZoneId(String zoneId);
}
