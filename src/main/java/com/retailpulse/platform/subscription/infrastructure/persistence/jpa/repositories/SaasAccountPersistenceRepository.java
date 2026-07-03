package com.retailpulse.platform.subscription.infrastructure.persistence.jpa.repositories;

import com.retailpulse.platform.subscription.infrastructure.persistence.jpa.entities.SaasAccountPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SaasAccountPersistenceRepository extends JpaRepository<SaasAccountPersistenceEntity, Long> {
    Optional<SaasAccountPersistenceEntity> findFirstByOwnerEmailIgnoreCase(String ownerEmail);
}
