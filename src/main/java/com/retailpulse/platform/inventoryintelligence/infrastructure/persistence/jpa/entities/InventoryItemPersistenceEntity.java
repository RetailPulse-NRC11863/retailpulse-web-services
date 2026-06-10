package com.retailpulse.platform.inventoryintelligence.infrastructure.persistence.jpa.entities;

import com.retailpulse.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import com.retailpulse.platform.inventoryintelligence.domain.model.valueobjects.StockStatus;

@Entity
@Table(name = "inventory_items")
public class InventoryItemPersistenceEntity extends AuditableAbstractPersistenceEntity {
    private Long productId;

    private Integer availableStock;

    private Integer criticalThreshold;

    @Enumerated(EnumType.STRING)
    private StockStatus status;

    public InventoryItemPersistenceEntity() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(Integer availableStock) {
        this.availableStock = availableStock;
    }

    public Integer getCriticalThreshold() {
        return criticalThreshold;
    }

    public void setCriticalThreshold(Integer criticalThreshold) {
        this.criticalThreshold = criticalThreshold;
    }

    public StockStatus getStatus() {
        return status;
    }

    public void setStatus(StockStatus status) {
        this.status = status;
    }


}
