package com.retailpulse.platform.inventoryintelligence.domain.model.aggregates;

import com.retailpulse.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.retailpulse.platform.inventoryintelligence.domain.model.valueobjects.StockStatus;

public class InventoryItem extends AuditableAbstractAggregateRoot {
    private Long productId;
    private Integer availableStock;
    private Integer criticalThreshold;
    private StockStatus status;

    public InventoryItem() {
    }

    public InventoryItem(Long id, Long productId, Integer availableStock, Integer criticalThreshold, StockStatus status) {
        setId(id);
        this.productId = productId;
        this.availableStock = availableStock;
        this.criticalThreshold = criticalThreshold;
        this.status = status;
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


    public void updateStock(Integer availableStock) {
        this.availableStock = availableStock;
        this.status = availableStock <= 0 ? StockStatus.OUT_OF_STOCK : availableStock <= criticalThreshold ? StockStatus.LOW_STOCK : StockStatus.AVAILABLE;
    }

}
