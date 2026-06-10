package com.retailpulse.platform.subscription.infrastructure.persistence.jpa.repositories;

import com.retailpulse.platform.subscription.infrastructure.persistence.jpa.entities.SaasAccountPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaasAccountPersistenceRepository extends JpaRepository<SaasAccountPersistenceEntity, Long> {

}
