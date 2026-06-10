package com.retailpulse.platform.subscription.domain.model.aggregates;

import com.retailpulse.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.retailpulse.platform.subscription.domain.model.valueobjects.SubscriptionStatus;

public class SaasAccount extends AuditableAbstractAggregateRoot {
    private Long storeId;
    private String ownerEmail;
    private Long planId;
    private SubscriptionStatus status;

    public SaasAccount() {
    }

    public SaasAccount(Long id, Long storeId, String ownerEmail, Long planId, SubscriptionStatus status) {
        setId(id);
        this.storeId = storeId;
        this.ownerEmail = ownerEmail;
        this.planId = planId;
        this.status = status;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    public void setStatus(SubscriptionStatus status) {
        this.status = status;
    }


    public void changePlan(Long planId) { this.planId = planId; }

}
