package com.retailpulse.platform.storeoperations.interfaces.rest.resources;

import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertStatus;
import jakarta.validation.constraints.NotNull;

public record ChangeOperationalAlertStatusResource(@NotNull AlertStatus status) {
}
