package com.retailpulse.platform.assistedshopping.domain.model.aggregates;

import com.retailpulse.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.retailpulse.platform.assistedshopping.domain.model.valueobjects.SessionStatus;

public class KioskSession extends AuditableAbstractAggregateRoot {
    private Long storeId;
    private SessionStatus status;

    public KioskSession() {
    }

    public KioskSession(Long id, Long storeId, SessionStatus status) {
        setId(id);
        this.storeId = storeId;
        this.status = status;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }


    public void finish() { this.status = SessionStatus.COMPLETED; }

}
