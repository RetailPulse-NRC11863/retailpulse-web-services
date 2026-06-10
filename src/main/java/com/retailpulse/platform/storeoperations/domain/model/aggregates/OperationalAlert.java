package com.retailpulse.platform.storeoperations.domain.model.aggregates;

import com.retailpulse.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertType;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertStatus;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.PriorityLevel;

public class OperationalAlert extends AuditableAbstractAggregateRoot {
    private Long storeId;
    private String title;
    private String description;
    private AlertType type;
    private AlertStatus status;
    private PriorityLevel priority;

    public OperationalAlert() {
    }

    public OperationalAlert(Long id, Long storeId, String title, String description, AlertType type, AlertStatus status, PriorityLevel priority) {
        setId(id);
        this.storeId = storeId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.status = status;
        this.priority = priority;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AlertType getType() {
        return type;
    }

    public void setType(AlertType type) {
        this.type = type;
    }

    public AlertStatus getStatus() {
        return status;
    }

    public void setStatus(AlertStatus status) {
        this.status = status;
    }

    public PriorityLevel getPriority() {
        return priority;
    }

    public void setPriority(PriorityLevel priority) {
        this.priority = priority;
    }


    public void resolve() { this.status = AlertStatus.RESOLVED; }

}
