package com.retailpulse.platform.subscription.domain.model.commands;

public record ChangeSubscriptionPlanCommand(Long accountId, Long planId) {
}
