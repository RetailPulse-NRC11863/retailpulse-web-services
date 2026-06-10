package com.retailpulse.platform.subscription.interfaces.rest.resources;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateSaasAccountResource(@NotNull Long storeId, @Email @NotBlank String ownerEmail, @NotNull Long planId) {
}
