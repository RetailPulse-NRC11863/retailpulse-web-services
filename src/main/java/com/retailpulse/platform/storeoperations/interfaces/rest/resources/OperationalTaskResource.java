package com.retailpulse.platform.storeoperations.interfaces.rest.resources;

import com.retailpulse.platform.storeoperations.domain.model.valueobjects.PriorityLevel;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.TaskStatus;

public record OperationalTaskResource(Long id, Long storeId, String title, String description, TaskStatus status, PriorityLevel priority) {
}
