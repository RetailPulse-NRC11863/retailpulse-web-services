package com.retailpulse.platform.trafficanalytics.interfaces.rest.transform;

import com.retailpulse.platform.trafficanalytics.domain.model.commands.UpsertZoneMetricCommand;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.UpsertZoneMetricResource;

public class UpsertZoneMetricCommandFromResourceAssembler {
    public static UpsertZoneMetricCommand toCommand(Long zoneId, UpsertZoneMetricResource resource) {
        return new UpsertZoneMetricCommand(zoneId, resource.trafficCount(), resource.averageDwellTime(), resource.interactionCount(), resource.conversionRate(), resource.intensity());
    }
}
