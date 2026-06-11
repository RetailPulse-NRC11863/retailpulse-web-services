package com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.repositories;

import com.retailpulse.platform.trafficanalytics.infrastructure.persistence.jpa.entities.StoreLayoutPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StoreLayoutPersistenceRepository extends JpaRepository<StoreLayoutPersistenceEntity, String> {
    Optional<StoreLayoutPersistenceEntity> findFirstByActiveTrue();
}
