package com.retailpulse.platform.storeoperations.interfaces.rest.resources;

import com.retailpulse.platform.storeoperations.domain.model.valueobjects.PriorityLevel;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateOperationalTaskResource(@NotBlank String title, @NotBlank String description, @NotNull PriorityLevel priority, @NotNull TaskStatus status, @NotBlank String zoneId, @NotBlank String zoneName, @NotBlank String alertId) {
}
