package com.retailpulse.platform.storefoundation.domain.model.entities;

import com.retailpulse.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.retailpulse.platform.storefoundation.domain.model.valueobjects.ZoneType;

public class Zone extends AuditableAbstractAggregateRoot {
    private Long storeId;
    private String name;
    private ZoneType type;
    private Integer capacity;

    public Zone() {
    }

    public Zone(Long id, Long storeId, String name, ZoneType type, Integer capacity) {
        setId(id);
        this.storeId = storeId;
        this.name = name;
        this.type = type;
        this.capacity = capacity;
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

    public ZoneType getType() {
        return type;
    }

    public void setType(ZoneType type) {
        this.type = type;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }


    public void update(String name, ZoneType type, Integer capacity) {
        this.name = name;
        this.type = type;
        this.capacity = capacity;
    }

}
