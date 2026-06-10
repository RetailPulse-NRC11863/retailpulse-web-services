package com.retailpulse.platform.storefoundation.domain.model.aggregates;

import com.retailpulse.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import java.math.BigDecimal;
import com.retailpulse.platform.storefoundation.domain.model.valueobjects.ProductLocation;
import com.retailpulse.platform.storefoundation.domain.model.valueobjects.ProductStatus;

public class Product extends AuditableAbstractAggregateRoot {
    private Long storeId;
    private String name;
    private String sku;
    private String category;
    private String description;
    private BigDecimal price;
    private ProductStatus status;
    private Long zoneId;
    private ProductLocation location;

    public Product() {
    }

    public Product(Long id, Long storeId, String name, String sku, String category, String description, BigDecimal price, ProductStatus status, Long zoneId, ProductLocation location) {
        setId(id);
        this.storeId = storeId;
        this.name = name;
        this.sku = sku;
        this.category = category;
        this.description = description;
        this.price = price;
        this.status = status;
        this.zoneId = zoneId;
        this.location = location;
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

    public ProductLocation getLocation() {
        return location;
    }

    public void setLocation(ProductLocation location) {
        this.location = location;
    }


    public void update(String name, String category, String description, BigDecimal price, ProductStatus status, Long zoneId, ProductLocation location) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.status = status;
        this.zoneId = zoneId;
        this.location = location;
    }

}
