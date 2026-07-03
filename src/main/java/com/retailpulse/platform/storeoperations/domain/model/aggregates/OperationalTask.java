package com.retailpulse.platform.storeoperations.domain.model.aggregates;

import com.retailpulse.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.TaskStatus;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.PriorityLevel;

public class OperationalTask extends AuditableAbstractAggregateRoot {
    private Long storeId;
    private String title;
    private String description;
    private TaskStatus status;
    private PriorityLevel priority;
    private Long alertId;
    private Long productId;
    private Long zoneId;
    private String source;
    private String triggerReason;

    public OperationalTask() {
    }

    public OperationalTask(Long id, Long storeId, String title, String description, TaskStatus status, PriorityLevel priority) {
        this(id, storeId, title, description, status, priority, null, null, null, "MANUAL", description);
    }

    public OperationalTask(Long id, Long storeId, String title, String description, TaskStatus status, PriorityLevel priority, Long alertId, Long productId, Long zoneId, String source, String triggerReason) {
        setId(id);
        this.storeId = storeId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.alertId = alertId;
        this.productId = productId;
        this.zoneId = zoneId;
        this.source = source;
        this.triggerReason = triggerReason;
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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public PriorityLevel getPriority() {
        return priority;
    }

    public void setPriority(PriorityLevel priority) {
        this.priority = priority;
    }

    public Long getAlertId() {
        return alertId;
    }

    public void setAlertId(Long alertId) {
        this.alertId = alertId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getZoneId() {
        return zoneId;
    }

    public void setZoneId(Long zoneId) {
        this.zoneId = zoneId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTriggerReason() {
        return triggerReason;
    }

    public void setTriggerReason(String triggerReason) {
        this.triggerReason = triggerReason;
    }

    public void complete() { this.status = TaskStatus.COMPLETED; }

}
