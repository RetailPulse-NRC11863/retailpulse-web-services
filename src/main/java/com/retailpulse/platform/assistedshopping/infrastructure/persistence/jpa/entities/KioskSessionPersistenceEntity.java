package com.retailpulse.platform.assistedshopping.infrastructure.persistence.jpa.entities;

import com.retailpulse.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import com.retailpulse.platform.assistedshopping.domain.model.valueobjects.SessionStatus;

@Entity
@Table(name = "kiosk_sessions")
public class KioskSessionPersistenceEntity extends AuditableAbstractPersistenceEntity {
    private Long storeId;

    @Enumerated(EnumType.STRING)
    private SessionStatus status;

    public KioskSessionPersistenceEntity() {
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


}
