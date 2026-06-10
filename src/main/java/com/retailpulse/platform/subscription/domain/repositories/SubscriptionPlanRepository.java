package com.retailpulse.platform.subscription.domain.repositories;

import com.retailpulse.platform.subscription.domain.model.aggregates.SubscriptionPlan;
import java.util.List;
import java.util.Optional;

public interface SubscriptionPlanRepository {
    SubscriptionPlan save(SubscriptionPlan plan);
    List<SubscriptionPlan> findAll();
    Optional<SubscriptionPlan> findById(Long planId);
    long count();
}
