package com.retailpulse.platform.storeoperations.interfaces.rest.resources;

import com.retailpulse.platform.storeoperations.domain.model.valueobjects.PriorityLevel;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.TaskStatus;
import java.time.Instant;

public record OperationalTaskResource(String id, String title, String description, PriorityLevel priority, TaskStatus status, String zoneId, String zoneName, String alertId, Instant createdAt) {
}
