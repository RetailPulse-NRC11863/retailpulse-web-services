package com.retailpulse.platform.storeoperations.interfaces.rest.resources;

import com.retailpulse.platform.storeoperations.domain.model.valueobjects.PriorityLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateOperationalTaskResource(@NotNull Long storeId, @NotBlank String title, String description, @NotNull PriorityLevel priority, Long alertId, Long productId, Long zoneId, String source, String triggerReason) {
}
