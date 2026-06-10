package com.retailpulse.platform.subscription.infrastructure.persistence.jpa.entities;

import com.retailpulse.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import com.retailpulse.platform.subscription.domain.model.valueobjects.PlanName;
import com.retailpulse.platform.subscription.domain.model.valueobjects.BillingPeriod;
import java.math.BigDecimal;

@Entity
@Table(name = "subscription_plans")
public class SubscriptionPlanPersistenceEntity extends AuditableAbstractPersistenceEntity {
    @Enumerated(EnumType.STRING)
    private PlanName name;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private BillingPeriod billingPeriod;

    private Integer maxStores;

    @Column(length = 1000)
    private String description;

    public SubscriptionPlanPersistenceEntity() {
    }

    public PlanName getName() {
        return name;
    }

    public void setName(PlanName name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BillingPeriod getBillingPeriod() {
        return billingPeriod;
    }

    public void setBillingPeriod(BillingPeriod billingPeriod) {
        this.billingPeriod = billingPeriod;
    }

    public Integer getMaxStores() {
        return maxStores;
    }

    public void setMaxStores(Integer maxStores) {
        this.maxStores = maxStores;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
