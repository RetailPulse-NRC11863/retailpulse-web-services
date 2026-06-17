package com.retailpulse.platform.storeoperations.domain.model.aggregates;

import com.retailpulse.platform.storeoperations.domain.model.valueobjects.PriorityLevel;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.TaskStatus;
import java.time.Instant;

public class OperationalTask {
    private String id;
    private String title;
    private String description;
    private PriorityLevel priority;
    private TaskStatus status;
    private String zoneId;
    private String zoneName;
    private String alertId;
    private Instant createdAt;

    public OperationalTask() {
    }

    public OperationalTask(String id, String title, String description, PriorityLevel priority, TaskStatus status, String zoneId, String zoneName, String alertId, Instant createdAt) {
        validateId(id);
        validateRequired(title, "Task title is required");
        validateRequired(description, "Task description is required");
        validateRequired(zoneId, "Task zone id is required");
        validateRequired(zoneName, "Task zone name is required");
        validateRequired(alertId, "Task alert id is required");
        if (priority == null) throw new IllegalArgumentException("Task priority is required");
        if (status == null) throw new IllegalArgumentException("Task status is required");
        if (createdAt == null) throw new IllegalArgumentException("Task createdAt is required");
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.zoneId = zoneId;
        this.zoneName = zoneName;
        this.alertId = alertId;
        this.createdAt = createdAt;
    }

    public void updateInformation(String title, String description, PriorityLevel priority, TaskStatus status, String zoneId, String zoneName, String alertId) {
        validateRequired(title, "Task title is required");
        validateRequired(description, "Task description is required");
        validateRequired(zoneId, "Task zone id is required");
        validateRequired(zoneName, "Task zone name is required");
        validateRequired(alertId, "Task alert id is required");
        if (priority == null) throw new IllegalArgumentException("Task priority is required");
        if (status == null) throw new IllegalArgumentException("Task status is required");
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.zoneId = zoneId;
        this.zoneName = zoneName;
        this.alertId = alertId;
    }

    public void changeStatus(TaskStatus status) {
        if (status == null) throw new IllegalArgumentException("Task status is required");
        this.status = status;
    }

    public void complete() {
        this.status = TaskStatus.COMPLETED;
    }

    public boolean isPending() {
        return status == TaskStatus.PENDING || status == TaskStatus.IN_PROGRESS;
    }

    private void validateId(String id) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Task id is required");
    }

    private void validateRequired(String value, String message) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(message);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public PriorityLevel getPriority() {
        return priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getZoneId() {
        return zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public String getAlertId() {
        return alertId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
