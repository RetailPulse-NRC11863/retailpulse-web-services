package com.retailpulse.platform.subscription.domain.model.commands;

public record CreateSaasAccountCommand(Long storeId, String ownerEmail, Long planId) {
}
