package com.retailpulse.platform.storeoperations.interfaces.rest.resources;

import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertStatus;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertType;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.PriorityLevel;

public record OperationalAlertResource(Long id, Long storeId, String title, String description, AlertType type, AlertStatus status, PriorityLevel priority, Long productId, Long zoneId, String source, String triggerReason) {
}
