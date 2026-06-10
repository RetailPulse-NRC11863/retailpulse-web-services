package com.retailpulse.platform.subscription.infrastructure.persistence.jpa.assemblers;

import com.retailpulse.platform.subscription.domain.model.aggregates.SubscriptionPlan;
import com.retailpulse.platform.subscription.infrastructure.persistence.jpa.entities.SubscriptionPlanPersistenceEntity;

public class SubscriptionPlanPersistenceAssembler {
    public SubscriptionPlan toDomain(SubscriptionPlanPersistenceEntity e) { return new SubscriptionPlan(e.getId(), e.getName(), e.getPrice(), e.getBillingPeriod(), e.getMaxStores(), e.getDescription()); }
    public SubscriptionPlanPersistenceEntity toEntity(SubscriptionPlan p) { SubscriptionPlanPersistenceEntity e = new SubscriptionPlanPersistenceEntity(); e.setId(p.getId()); e.setName(p.getName()); e.setPrice(p.getPrice()); e.setBillingPeriod(p.getBillingPeriod()); e.setMaxStores(p.getMaxStores()); e.setDescription(p.getDescription()); return e; }
}
