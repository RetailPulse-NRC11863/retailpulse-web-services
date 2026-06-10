package com.retailpulse.platform.subscription.domain.model.commands;

import com.retailpulse.platform.subscription.domain.model.valueobjects.PlanName;
import com.retailpulse.platform.subscription.domain.model.valueobjects.BillingPeriod;
import java.math.BigDecimal;

public record CreateSubscriptionPlanCommand(PlanName name, BigDecimal price, BillingPeriod billingPeriod, Integer maxStores, String description) {
}
