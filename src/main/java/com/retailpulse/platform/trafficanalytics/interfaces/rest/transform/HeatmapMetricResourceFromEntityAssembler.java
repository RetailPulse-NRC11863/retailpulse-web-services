package com.retailpulse.platform.trafficanalytics.interfaces.rest.transform;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.ZoneMetric;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.HeatmapMetricResource;

public class HeatmapMetricResourceFromEntityAssembler {
    public static HeatmapMetricResource toResource(ZoneMetric metric) { return new HeatmapMetricResource(metric.getZoneId(), metric.getTrafficCount(), metric.getConversionRate(), metric.getHeatLevel()); }
}
