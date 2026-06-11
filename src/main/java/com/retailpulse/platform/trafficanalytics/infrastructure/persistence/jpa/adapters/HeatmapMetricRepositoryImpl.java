package com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.adapters;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.HeatmapMetric;
import com.retailpulse.platform.trafficanalytics.domain.repositories.HeatmapMetricRepository;
import com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.assemblers.HeatmapMetricPersistenceAssembler;
import com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.repositories.HeatmapMetricPersistenceRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class HeatmapMetricRepositoryImpl implements HeatmapMetricRepository {
    private final HeatmapMetricPersistenceRepository repository;

    public HeatmapMetricRepositoryImpl(HeatmapMetricPersistenceRepository repository) {
        this.repository = repository;
    }

    public List<HeatmapMetric> findAll() {
        return repository.findAll().stream().map(HeatmapMetricPersistenceAssembler::toDomainFromPersistence).toList();
    }

    public Optional<HeatmapMetric> findById(String id) {
        return repository.findById(id).map(HeatmapMetricPersistenceAssembler::toDomainFromPersistence);
    }

    public List<HeatmapMetric> findByZoneId(String zoneId) {
        return repository.findByZoneId(zoneId).stream().map(HeatmapMetricPersistenceAssembler::toDomainFromPersistence).toList();
    }

    public HeatmapMetric save(HeatmapMetric heatmapMetric) {
        return HeatmapMetricPersistenceAssembler.toDomainFromPersistence(repository.save(HeatmapMetricPersistenceAssembler.toPersistenceFromDomain(heatmapMetric)));
    }

    public boolean existsById(String id) {
        return repository.existsById(id);
    }
}
