package com.retailpulse.platform.trafficanalytics.application.commandservices;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.ZoneMetric;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.UpsertZoneMetricCommand;

public interface ZoneMetricCommandService {
    ZoneMetric handle(UpsertZoneMetricCommand command);
}
