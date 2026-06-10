package com.retailpulse.platform.subscription.interfaces.rest.transform;

import com.retailpulse.platform.subscription.domain.model.aggregates.SubscriptionPlan;
import com.retailpulse.platform.subscription.interfaces.rest.resources.SubscriptionPlanResource;

public class SubscriptionPlanResourceFromEntityAssembler {
    public static SubscriptionPlanResource toResource(SubscriptionPlan p) { return new SubscriptionPlanResource(p.getId(), p.getName(), p.getPrice(), p.getBillingPeriod(), p.getMaxStores(), p.getDescription()); }
}
