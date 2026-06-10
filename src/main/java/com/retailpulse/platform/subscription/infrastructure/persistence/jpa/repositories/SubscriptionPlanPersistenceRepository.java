package com.retailpulse.platform.subscription.infrastructure.persistence.jpa.repositories;

import com.retailpulse.platform.subscription.infrastructure.persistence.jpa.entities.SubscriptionPlanPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionPlanPersistenceRepository extends JpaRepository<SubscriptionPlanPersistenceEntity, Long> {

}
