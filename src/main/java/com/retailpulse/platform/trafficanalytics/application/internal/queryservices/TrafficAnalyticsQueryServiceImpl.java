package com.retailpulse.platform.trafficanalytics.application.internal.queryservices;

import com.retailpulse.platform.trafficanalytics.application.queryservices.TrafficAnalyticsQueryService;
import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.ZoneMetric;
import com.retailpulse.platform.trafficanalytics.domain.repositories.ZoneMetricRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrafficAnalyticsQueryServiceImpl implements TrafficAnalyticsQueryService {
    private final ZoneMetricRepository repository;
    public TrafficAnalyticsQueryServiceImpl(ZoneMetricRepository repository) { this.repository = repository; }
    public List<ZoneMetric> heatmap() { return repository.findAll(); }
    public List<ZoneMetric> zoneMetrics() { return repository.findAll(); }
    public List<ZoneMetric> congestion() { return repository.findAll(); }
}
