package com.retailpulse.platform.trafficanalytics.interfaces.rest.transform;

import com.retailpulse.platform.trafficanalytics.domain.model.commands.CreateHeatmapMetricCommand;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.CreateHeatmapMetricResource;

public class CreateHeatmapMetricCommandFromResourceAssembler {
    public static CreateHeatmapMetricCommand toCommand(CreateHeatmapMetricResource resource) {
        return new CreateHeatmapMetricCommand(resource.id(), resource.zoneId(), resource.traffic(), resource.averageDwellTimeSeconds(), resource.conversionRate(), resource.intensity(), resource.attentionRequired());
    }
}
