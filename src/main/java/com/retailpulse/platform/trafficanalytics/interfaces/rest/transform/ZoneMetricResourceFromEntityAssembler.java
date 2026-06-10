package com.retailpulse.platform.trafficanalytics.interfaces.rest.transform;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.ZoneMetric;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.ZoneMetricResource;

public class ZoneMetricResourceFromEntityAssembler {
    public static ZoneMetricResource toResource(ZoneMetric metric) { return new ZoneMetricResource(metric.getZoneId(), metric.getTrafficCount(), metric.getAverageDwellTime(), metric.getInteractionCount(), metric.getConversionRate(), metric.getHeatLevel(), metric.getCongestionStatus()); }
}
