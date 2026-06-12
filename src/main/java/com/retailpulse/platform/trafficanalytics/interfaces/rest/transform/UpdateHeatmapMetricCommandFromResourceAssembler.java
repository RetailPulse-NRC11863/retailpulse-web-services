package com.retailpulse.platform.trafficanalytics.interfaces.rest.transform;

import com.retailpulse.platform.trafficanalytics.domain.model.commands.UpdateHeatmapMetricCommand;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.UpdateHeatmapMetricResource;

public class UpdateHeatmapMetricCommandFromResourceAssembler {
    public static UpdateHeatmapMetricCommand toCommand(String metricId, UpdateHeatmapMetricResource resource) {
        return new UpdateHeatmapMetricCommand(metricId, resource.zoneId(), resource.traffic(), resource.averageDwellTimeSeconds(), resource.conversionRate(), resource.intensity(), resource.attentionRequired());
    }
}
