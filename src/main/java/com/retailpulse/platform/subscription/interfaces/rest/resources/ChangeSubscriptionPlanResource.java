package com.retailpulse.platform.subscription.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

public record ChangeSubscriptionPlanResource(@NotNull Long planId) {
}
