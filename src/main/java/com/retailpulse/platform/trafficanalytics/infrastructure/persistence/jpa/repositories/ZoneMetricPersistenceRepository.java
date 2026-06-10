package com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.repositories;

import com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.entities.ZoneMetricPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneMetricPersistenceRepository extends JpaRepository<ZoneMetricPersistenceEntity, Long> {

}
