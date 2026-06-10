package com.retailpulse.platform.storefoundation.infrastructure.persistence.jpa.repositories;

import com.retailpulse.platform.storefoundation.infrastructure.persistence.jpa.entities.StorePersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorePersistenceRepository extends JpaRepository<StorePersistenceEntity, Long> {

}
