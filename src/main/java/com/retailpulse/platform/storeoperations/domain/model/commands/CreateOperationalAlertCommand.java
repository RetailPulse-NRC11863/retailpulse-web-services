package com.retailpulse.platform.storeoperations.domain.model.commands;

import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertType;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.PriorityLevel;

public record CreateOperationalAlertCommand(Long storeId, String title, String description, AlertType type, PriorityLevel priority) {
}
