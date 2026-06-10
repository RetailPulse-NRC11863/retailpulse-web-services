package com.retailpulse.platform.subscription.interfaces.rest.resources;

import com.retailpulse.platform.subscription.domain.model.valueobjects.SubscriptionStatus;

public record SaasAccountResource(Long id, Long storeId, String ownerEmail, Long planId, SubscriptionStatus status) {
}
