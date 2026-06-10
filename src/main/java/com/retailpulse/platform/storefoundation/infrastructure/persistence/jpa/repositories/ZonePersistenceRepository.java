package com.retailpulse.platform.storefoundation.infrastructure.persistence.jpa.repositories;

import com.retailpulse.platform.storefoundation.infrastructure.persistence.jpa.entities.ZonePersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZonePersistenceRepository extends JpaRepository<ZonePersistenceEntity, Long> {

}
