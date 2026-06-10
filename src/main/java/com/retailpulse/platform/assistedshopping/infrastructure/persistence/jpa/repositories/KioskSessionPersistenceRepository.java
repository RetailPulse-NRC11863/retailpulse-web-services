package com.retailpulse.platform.assistedshopping.infrastructure.persistence.jpa.repositories;

import com.retailpulse.platform.assistedshopping.infrastructure.persistence.jpa.entities.KioskSessionPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KioskSessionPersistenceRepository extends JpaRepository<KioskSessionPersistenceEntity, Long> {

}
