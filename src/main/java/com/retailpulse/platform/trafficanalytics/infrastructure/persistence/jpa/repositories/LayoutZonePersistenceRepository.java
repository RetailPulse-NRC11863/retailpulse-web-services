package com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.repositories;

import com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.entities.LayoutZonePersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LayoutZonePersistenceRepository extends JpaRepository<LayoutZonePersistenceEntity, String> {
}
