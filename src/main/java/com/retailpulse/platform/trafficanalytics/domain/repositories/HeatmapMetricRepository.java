package com.retailpulse.platform.trafficanalytics.domain.repositories;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.HeatmapMetric;
import java.util.List;
import java.util.Optional;

public interface HeatmapMetricRepository {
    List<HeatmapMetric> findAll();
    Optional<HeatmapMetric> findById(String id);
    List<HeatmapMetric> findByZoneId(String zoneId);
    HeatmapMetric save(HeatmapMetric heatmapMetric);
    boolean existsById(String id);
}
