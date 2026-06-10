package com.retailpulse.platform.subscription.interfaces.rest.resources;

import com.retailpulse.platform.subscription.domain.model.valueobjects.BillingPeriod;
import com.retailpulse.platform.subscription.domain.model.valueobjects.PlanName;
import java.math.BigDecimal;

public record SubscriptionPlanResource(Long id, PlanName name, BigDecimal price, BillingPeriod billingPeriod, Integer maxStores, String description) {
}
