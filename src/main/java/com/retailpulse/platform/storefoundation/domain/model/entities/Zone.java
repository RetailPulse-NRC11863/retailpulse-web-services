package com.retailpulse.platform.storefoundation.domain.model.entities;

import com.retailpulse.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.retailpulse.platform.storefoundation.domain.model.valueobjects.ZoneType;

public class Zone extends AuditableAbstractAggregateRoot {
    private Long storeId;
    private String name;
    private ZoneType type;
    private Integer capacity;
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;

    public Zone() {
    }

    public Zone(Long id, Long storeId, String name, ZoneType type, Integer capacity) {
        this(id, storeId, name, type, capacity, 0, 0, 160, 100);
    }

    public Zone(Long id, Long storeId, String name, ZoneType type, Integer capacity, Integer x, Integer y, Integer width, Integer height) {
        setId(id);
        this.storeId = storeId;
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.x = x == null ? 0 : x;
        this.y = y == null ? 0 : y;
        this.width = width == null ? 160 : width;
        this.height = height == null ? 100 : height;
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

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }


    public void update(String name, ZoneType type, Integer capacity) {
        update(name, type, capacity, this.x, this.y, this.width, this.height);
    }

    public void update(String name, ZoneType type, Integer capacity, Integer x, Integer y, Integer width, Integer height) {
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.x = x == null ? this.x : x;
        this.y = y == null ? this.y : y;
        this.width = width == null ? this.width : width;
        this.height = height == null ? this.height : height;
    }

}
