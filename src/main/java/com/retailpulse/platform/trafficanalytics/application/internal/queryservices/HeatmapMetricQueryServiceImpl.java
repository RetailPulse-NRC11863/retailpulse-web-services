package com.retailpulse.platform.trafficanalytics.application.internal.queryservices;

import com.retailpulse.platform.trafficanalytics.application.queryservices.HeatmapMetricQueryService;
import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.HeatmapMetric;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetAllHeatmapMetricsQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetHeatmapMetricByIdQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetHeatmapMetricsByZoneIdQuery;
import com.retailpulse.platform.trafficanalytics.domain.repositories.HeatmapMetricRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HeatmapMetricQueryServiceImpl implements HeatmapMetricQueryService {
    private final HeatmapMetricRepository repository;

    public HeatmapMetricQueryServiceImpl(HeatmapMetricRepository repository) {
        this.repository = repository;
    }

    public List<HeatmapMetric> handle(GetAllHeatmapMetricsQuery query) {
        return repository.findAll();
    }

    public Optional<HeatmapMetric> handle(GetHeatmapMetricByIdQuery query) {
        return repository.findById(query.id());
    }

    public List<HeatmapMetric> handle(GetHeatmapMetricsByZoneIdQuery query) {
        return repository.findByZoneId(query.zoneId());
    }
}
