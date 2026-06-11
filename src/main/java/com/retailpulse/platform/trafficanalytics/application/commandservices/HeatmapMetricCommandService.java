package com.retailpulse.platform.trafficanalytics.application.commandservices;

import com.retailpulse.platform.shared.application.result.Result;
import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.HeatmapMetric;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.CreateHeatmapMetricCommand;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.UpdateHeatmapMetricCommand;

public interface HeatmapMetricCommandService {
    Result<HeatmapMetric> handle(CreateHeatmapMetricCommand command);
    Result<HeatmapMetric> handle(UpdateHeatmapMetricCommand command);
}
