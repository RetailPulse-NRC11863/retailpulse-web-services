package com.retailpulse.platform.trafficanalytics.domain.repositories;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.ZoneMetric;
import java.util.List;
import java.util.Optional;

public interface ZoneMetricRepository {
    ZoneMetric save(ZoneMetric metric);
    List<ZoneMetric> findAll();
    Optional<ZoneMetric> findByZoneId(Long zoneId);
    long count();
}
