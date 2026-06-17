package com.retailpulse.platform.storefoundation.infrastructure.persistence.jpa.entities;

import com.retailpulse.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import com.retailpulse.platform.storefoundation.domain.model.valueobjects.ProductStatus;

@Entity
@Table(name = "store_foundation_products")
public class ProductPersistenceEntity extends AuditableAbstractPersistenceEntity {
    private Long storeId;

    private String name;

    private String sku;

    private String category;

    @Column(length = 1000)
    private String description;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    private Long zoneId;

    private String aisle;

    private String shelf;

    private String displayReference;

    public ProductPersistenceEntity() {
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Long getZoneId() {
        return zoneId;
    }

    public void setZoneId(Long zoneId) {
        this.zoneId = zoneId;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public String getDisplayReference() {
        return displayReference;
    }

    public void setDisplayReference(String displayReference) {
        this.displayReference = displayReference;
    }


}
