package com.retailpulse.platform.subscription.application.queryservices;

import com.retailpulse.platform.subscription.domain.model.aggregates.SubscriptionPlan;
import java.util.List;
import java.util.Optional;

public interface SubscriptionPlanQueryService {
    List<SubscriptionPlan> getAll();
    Optional<SubscriptionPlan> getById(Long planId);
}
