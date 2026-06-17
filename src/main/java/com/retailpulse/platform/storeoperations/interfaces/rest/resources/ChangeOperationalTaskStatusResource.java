package com.retailpulse.platform.storeoperations.interfaces.rest.resources;

import com.retailpulse.platform.storeoperations.domain.model.valueobjects.TaskStatus;
import jakarta.validation.constraints.NotNull;

public record ChangeOperationalTaskStatusResource(@NotNull TaskStatus status) {
}
