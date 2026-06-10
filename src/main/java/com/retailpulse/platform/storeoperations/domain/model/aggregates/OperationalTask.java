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

    public OperationalTask() {
    }

    public OperationalTask(Long id, Long storeId, String title, String description, TaskStatus status, PriorityLevel priority) {
        setId(id);
        this.storeId = storeId;
        this.title = title;
        this.description = description;
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


    public void complete() { this.status = TaskStatus.COMPLETED; }

}
