package com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.adapters;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.ZoneMetric;
import com.retailpulse.platform.trafficanalytics.domain.repositories.ZoneMetricRepository;
import com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.assemblers.ZoneMetricPersistenceAssembler;
import com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.repositories.ZoneMetricPersistenceRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ZoneMetricRepositoryImpl implements ZoneMetricRepository {
    private final ZoneMetricPersistenceRepository repository;
    private final ZoneMetricPersistenceAssembler assembler = new ZoneMetricPersistenceAssembler();
    public ZoneMetricRepositoryImpl(ZoneMetricPersistenceRepository repository) { this.repository = repository; }
    public ZoneMetric save(ZoneMetric metric) { return assembler.toDomain(repository.save(assembler.toEntity(metric))); }
    public List<ZoneMetric> findAll() { return repository.findAll().stream().map(assembler::toDomain).toList(); }
    public long count() { return repository.count(); }
}
