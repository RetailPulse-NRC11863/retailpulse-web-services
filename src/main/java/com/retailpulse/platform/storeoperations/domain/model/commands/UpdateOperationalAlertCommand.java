package com.retailpulse.platform.storeoperations.domain.model.commands;

import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertStatus;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertType;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.PriorityLevel;

public record UpdateOperationalAlertCommand(String id, AlertType type, PriorityLevel priority, AlertStatus status, String message, String zoneId, String zoneName, String productId, String productName) {
}
