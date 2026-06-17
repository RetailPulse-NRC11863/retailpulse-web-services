package com.retailpulse.platform.storeoperations.domain.model.aggregates;

import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertStatus;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertType;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.PriorityLevel;
import java.time.Instant;

public class OperationalAlert {
    private String id;
    private AlertType type;
    private PriorityLevel priority;
    private AlertStatus status;
    private String message;
    private String zoneId;
    private String zoneName;
    private String productId;
    private String productName;
    private Instant createdAt;

    public OperationalAlert() {
    }

    public OperationalAlert(String id, AlertType type, PriorityLevel priority, AlertStatus status, String message, String zoneId, String zoneName, String productId, String productName, Instant createdAt) {
        validateId(id);
        validateRequired(message, "Alert message is required");
        validateRequired(zoneId, "Alert zone id is required");
        validateRequired(zoneName, "Alert zone name is required");
        if (type == null) throw new IllegalArgumentException("Alert type is required");
        if (priority == null) throw new IllegalArgumentException("Alert priority is required");
        if (status == null) throw new IllegalArgumentException("Alert status is required");
        if (createdAt == null) throw new IllegalArgumentException("Alert createdAt is required");
        this.id = id;
        this.type = type;
        this.priority = priority;
        this.status = status;
        this.message = message;
        this.zoneId = zoneId;
        this.zoneName = zoneName;
        this.productId = productId;
        this.productName = productName;
        this.createdAt = createdAt;
    }

    public void updateInformation(AlertType type, PriorityLevel priority, AlertStatus status, String message, String zoneId, String zoneName, String productId, String productName) {
        validateRequired(message, "Alert message is required");
        validateRequired(zoneId, "Alert zone id is required");
        validateRequired(zoneName, "Alert zone name is required");
        if (type == null) throw new IllegalArgumentException("Alert type is required");
        if (priority == null) throw new IllegalArgumentException("Alert priority is required");
        if (status == null) throw new IllegalArgumentException("Alert status is required");
        this.type = type;
        this.priority = priority;
        this.status = status;
        this.message = message;
        this.zoneId = zoneId;
        this.zoneName = zoneName;
        this.productId = productId;
        this.productName = productName;
    }

    public void changeStatus(AlertStatus status) {
        if (status == null) throw new IllegalArgumentException("Alert status is required");
        this.status = status;
    }

    public void resolve() {
        this.status = AlertStatus.RESOLVED;
    }

    public boolean isActive() {
        return status == AlertStatus.PENDING || status == AlertStatus.IN_PROGRESS;
    }

    private void validateId(String id) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Alert id is required");
    }

    private void validateRequired(String value, String message) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(message);
    }

    public String getId() {
        return id;
    }

    public AlertType getType() {
        return type;
    }

    public PriorityLevel getPriority() {
        return priority;
    }

    public AlertStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getZoneId() {
        return zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
