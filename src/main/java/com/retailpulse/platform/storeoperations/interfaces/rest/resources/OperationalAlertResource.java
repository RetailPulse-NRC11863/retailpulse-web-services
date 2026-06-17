package com.retailpulse.platform.storeoperations.interfaces.rest.resources;

import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertStatus;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertType;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.PriorityLevel;
import java.time.Instant;

public record OperationalAlertResource(String id, AlertType type, PriorityLevel priority, AlertStatus status, String message, String zoneId, String zoneName, String productId, String productName, Instant createdAt) {
}
