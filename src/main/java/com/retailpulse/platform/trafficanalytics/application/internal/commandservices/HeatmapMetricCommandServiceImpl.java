package com.retailpulse.platform.trafficanalytics.application.internal.commandservices;

import com.retailpulse.platform.shared.application.result.ApplicationError;
import com.retailpulse.platform.shared.application.result.Result;
import com.retailpulse.platform.trafficanalytics.application.commandservices.HeatmapMetricCommandService;
import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.HeatmapMetric;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.CreateHeatmapMetricCommand;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.UpdateHeatmapMetricCommand;
import com.retailpulse.platform.trafficanalytics.domain.repositories.HeatmapMetricRepository;
import org.springframework.stereotype.Service;

@Service
public class HeatmapMetricCommandServiceImpl implements HeatmapMetricCommandService {
    private final HeatmapMetricRepository repository;

    public HeatmapMetricCommandServiceImpl(HeatmapMetricRepository repository) {
        this.repository = repository;
    }

    public Result<HeatmapMetric> handle(CreateHeatmapMetricCommand command) {
        if (repository.existsById(command.id())) {
            return Result.failure(ApplicationError.validation("Heatmap metric already exists"));
        }
        try {
            HeatmapMetric heatmapMetric = new HeatmapMetric(
                command.id(),
                command.zoneId(),
                command.traffic(),
                command.averageDwellTimeSeconds(),
                command.conversionRate(),
                command.intensity(),
                command.attentionRequired()
            );
            return Result.success(repository.save(heatmapMetric));
        } catch (IllegalArgumentException exception) {
            return Result.failure(ApplicationError.validation(exception.getMessage()));
        }
    }

    public Result<HeatmapMetric> handle(UpdateHeatmapMetricCommand command) {
        return repository.findById(command.id())
            .map(heatmapMetric -> updateHeatmapMetric(command, heatmapMetric))
            .orElseGet(() -> Result.failure(ApplicationError.notFound("Heatmap metric not found")));
    }

    private Result<HeatmapMetric> updateHeatmapMetric(UpdateHeatmapMetricCommand command, HeatmapMetric heatmapMetric) {
        try {
            heatmapMetric.update(
                command.zoneId(),
                command.traffic(),
                command.averageDwellTimeSeconds(),
                command.conversionRate(),
                command.intensity(),
                command.attentionRequired()
            );
            return Result.success(repository.save(heatmapMetric));
        } catch (IllegalArgumentException exception) {
            return Result.failure(ApplicationError.validation(exception.getMessage()));
        }
    }
}
