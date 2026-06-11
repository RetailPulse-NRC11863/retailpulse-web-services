package com.retailpulse.platform.trafficanalytics.application.queryservices;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.HeatmapMetric;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetAllHeatmapMetricsQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetHeatmapMetricByIdQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetHeatmapMetricsByZoneIdQuery;
import java.util.List;
import java.util.Optional;

public interface HeatmapMetricQueryService {
    List<HeatmapMetric> handle(GetAllHeatmapMetricsQuery query);
    Optional<HeatmapMetric> handle(GetHeatmapMetricByIdQuery query);
    List<HeatmapMetric> handle(GetHeatmapMetricsByZoneIdQuery query);
}
