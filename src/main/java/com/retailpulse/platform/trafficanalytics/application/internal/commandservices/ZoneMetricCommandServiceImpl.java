package com.retailpulse.platform.trafficanalytics.application.internal.commandservices;

import com.retailpulse.platform.trafficanalytics.application.commandservices.ZoneMetricCommandService;
import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.ZoneMetric;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.UpsertZoneMetricCommand;
import com.retailpulse.platform.trafficanalytics.domain.model.valueobjects.CongestionStatus;
import com.retailpulse.platform.trafficanalytics.domain.model.valueobjects.HeatLevel;
import com.retailpulse.platform.trafficanalytics.domain.repositories.ZoneMetricRepository;
import org.springframework.stereotype.Service;

@Service
public class ZoneMetricCommandServiceImpl implements ZoneMetricCommandService {
    private final ZoneMetricRepository repository;

    public ZoneMetricCommandServiceImpl(ZoneMetricRepository repository) {
        this.repository = repository;
    }

    public ZoneMetric handle(UpsertZoneMetricCommand command) {
        ZoneMetric metric = repository.findByZoneId(command.zoneId()).orElse(new ZoneMetric());
        metric.setZoneId(command.zoneId());
        metric.setTrafficCount(command.trafficCount() == null ? 0 : command.trafficCount());
        metric.setAverageDwellTime(command.averageDwellTime() == null ? 0.0 : command.averageDwellTime());
        metric.setInteractionCount(command.interactionCount() == null ? metric.getTrafficCount() : command.interactionCount());
        metric.setConversionRate((command.conversionRate() == null ? 0.0 : command.conversionRate()) / 100.0);
        metric.setHeatLevel(toHeatLevel(command.intensity()));
        metric.setCongestionStatus(toCongestionStatus(command.intensity()));
        return repository.save(metric);
    }

    private HeatLevel toHeatLevel(Integer intensity) {
        int value = intensity == null ? 0 : intensity;
        if (value >= 75) return HeatLevel.HOT;
        if (value >= 35) return HeatLevel.WARM;
        return HeatLevel.COLD;
    }

    private CongestionStatus toCongestionStatus(Integer intensity) {
        int value = intensity == null ? 0 : intensity;
        if (value >= 85) return CongestionStatus.HIGH;
        if (value >= 55) return CongestionStatus.MODERATE;
        return CongestionStatus.LOW;
    }
}
