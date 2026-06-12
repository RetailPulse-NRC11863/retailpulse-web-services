package com.retailpulse.platform.trafficanalytics.interfaces.rest.transform;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.HeatmapMetric;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.HeatmapMetricResource;

public class HeatmapMetricResourceFromEntityAssembler {
    public static HeatmapMetricResource toResource(HeatmapMetric metric) {
        return new HeatmapMetricResource(
            metric.getId(),
            metric.getZoneId(),
            metric.getTraffic(),
            metric.getAverageDwellTimeSeconds(),
            metric.getConversionRate(),
            metric.getIntensity(),
            metric.getAttentionRequired()
        );
    }
}
