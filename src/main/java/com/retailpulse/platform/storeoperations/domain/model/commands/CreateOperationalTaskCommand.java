package com.retailpulse.platform.storeoperations.domain.model.commands;

import com.retailpulse.platform.storeoperations.domain.model.valueobjects.PriorityLevel;

public record CreateOperationalTaskCommand(Long storeId, String title, String description, PriorityLevel priority) {
}
