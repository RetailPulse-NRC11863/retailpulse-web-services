package com.retailpulse.platform.trafficanalytics.domain.repositories;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.ZoneMetric;
import java.util.List;

public interface ZoneMetricRepository {
    ZoneMetric save(ZoneMetric metric);
    List<ZoneMetric> findAll();
    long count();
}
