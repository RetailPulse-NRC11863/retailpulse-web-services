package com.retailpulse.platform.trafficanalytics.interfaces.rest.transform;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.ZoneMetric;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.LegacyHeatmapMetricResource;

public class LegacyHeatmapMetricResourceFromEntityAssembler {
    public static LegacyHeatmapMetricResource toResource(ZoneMetric metric) {
        return new LegacyHeatmapMetricResource(metric.getZoneId(), metric.getTrafficCount(), metric.getConversionRate(), metric.getHeatLevel());
    }
}
