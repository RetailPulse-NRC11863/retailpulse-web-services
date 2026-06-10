package com.retailpulse.platform.subscription.domain.model.aggregates;

import com.retailpulse.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.retailpulse.platform.subscription.domain.model.valueobjects.PlanName;
import com.retailpulse.platform.subscription.domain.model.valueobjects.BillingPeriod;
import java.math.BigDecimal;

public class SubscriptionPlan extends AuditableAbstractAggregateRoot {
    private PlanName name;
    private BigDecimal price;
    private BillingPeriod billingPeriod;
    private Integer maxStores;
    private String description;

    public SubscriptionPlan() {
    }

    public SubscriptionPlan(Long id, PlanName name, BigDecimal price, BillingPeriod billingPeriod, Integer maxStores, String description) {
        setId(id);
        this.name = name;
        this.price = price;
        this.billingPeriod = billingPeriod;
        this.maxStores = maxStores;
        this.description = description;
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
