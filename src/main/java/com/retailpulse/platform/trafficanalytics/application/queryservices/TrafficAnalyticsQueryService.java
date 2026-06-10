package com.retailpulse.platform.trafficanalytics.application.queryservices;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.ZoneMetric;
import java.util.List;

public interface TrafficAnalyticsQueryService {
    List<ZoneMetric> heatmap();
    List<ZoneMetric> zoneMetrics();
    List<ZoneMetric> congestion();
}
