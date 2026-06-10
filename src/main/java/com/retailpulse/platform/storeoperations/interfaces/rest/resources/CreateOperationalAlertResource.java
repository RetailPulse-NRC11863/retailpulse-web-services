package com.retailpulse.platform.storeoperations.interfaces.rest.resources;

import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertType;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.PriorityLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateOperationalAlertResource(@NotNull Long storeId, @NotBlank String title, String description, @NotNull AlertType type, @NotNull PriorityLevel priority) {
}
