package com.retailpulse.platform.storeoperations.interfaces.rest.resources;

import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertStatus;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertType;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.PriorityLevel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.time.Instant;

public record CreateOperationalAlertResource(@NotBlank String id, @NotNull AlertType type, @NotNull PriorityLevel priority, @NotNull AlertStatus status, @NotBlank String message, @NotBlank String zoneId, @NotBlank String zoneName, String productId, String productName, Instant createdAt) {
}
