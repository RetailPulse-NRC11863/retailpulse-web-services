package com.retailpulse.platform.storeoperations.domain.model.commands;

import com.retailpulse.platform.storeoperations.domain.model.valueobjects.PriorityLevel;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.TaskStatus;

public record UpdateOperationalTaskCommand(String id, String title, String description, PriorityLevel priority, TaskStatus status, String zoneId, String zoneName, String alertId) {
}
