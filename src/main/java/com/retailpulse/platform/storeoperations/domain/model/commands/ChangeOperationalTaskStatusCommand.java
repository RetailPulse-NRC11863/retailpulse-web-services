package com.retailpulse.platform.storeoperations.domain.model.commands;

import com.retailpulse.platform.storeoperations.domain.model.valueobjects.TaskStatus;

public record ChangeOperationalTaskStatusCommand(String id, TaskStatus status) {
}
