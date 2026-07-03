package com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.repositories;

import com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.entities.ZoneMetricPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ZoneMetricPersistenceRepository extends JpaRepository<ZoneMetricPersistenceEntity, Long> {
    Optional<ZoneMetricPersistenceEntity> findByZoneId(Long zoneId);

}
