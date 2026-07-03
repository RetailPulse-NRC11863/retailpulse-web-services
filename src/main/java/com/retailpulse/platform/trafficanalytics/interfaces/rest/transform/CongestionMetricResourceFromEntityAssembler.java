package com.retailpulse.platform.trafficanalytics.interfaces.rest.transform;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.ZoneMetric;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.CongestionMetricResource;

public class CongestionMetricResourceFromEntityAssembler {
    public static CongestionMetricResource toResource(ZoneMetric metric) {
        return new CongestionMetricResource(
            metric.getZoneId(),
            metric.getCongestionStatus(),
            metric.getTrafficCount(),
            metric.getInteractionCount(),
            metric.getConversionRate()
        );
    }
}
