package com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.repositories;

import com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.entities.MovementEventPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementEventPersistenceRepository extends JpaRepository<MovementEventPersistenceEntity, Long> {

}
