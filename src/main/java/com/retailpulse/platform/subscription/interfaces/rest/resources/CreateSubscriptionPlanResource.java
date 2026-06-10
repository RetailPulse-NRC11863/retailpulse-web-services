package com.retailpulse.platform.subscription.interfaces.rest.resources;

import com.retailpulse.platform.subscription.domain.model.valueobjects.BillingPeriod;
import com.retailpulse.platform.subscription.domain.model.valueobjects.PlanName;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record CreateSubscriptionPlanResource(@NotNull PlanName name, @PositiveOrZero BigDecimal price, @NotNull BillingPeriod billingPeriod, @PositiveOrZero Integer maxStores, String description) {
}
