package com.retailpulse.platform.trafficanalytics.domain.model.aggregates;

import com.retailpulse.platform.trafficanalytics.domain.model.valueobjects.ZoneType;

public class LayoutZone {
    private String id;
    private String name;
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;
    private ZoneType type;

    public LayoutZone() {
    }

    public LayoutZone(String id, String name, Integer x, Integer y, Integer width, Integer height, ZoneType type) {
        validateId(id);
        validateName(name);
        validatePosition(x, y);
        validateSize(width, height);
        validateType(type);
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
    }

    public void update(String name, Integer x, Integer y, Integer width, Integer height, ZoneType type) {
        validateName(name);
        validatePosition(x, y);
        validateSize(width, height);
        validateType(type);
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
    }

    private void validateId(String id) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Layout zone id is required");
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Layout zone name is required");
    }

    private void validatePosition(Integer x, Integer y) {
        if (x == null || x < 0) throw new IllegalArgumentException("Layout zone x must be zero or positive");
        if (y == null || y < 0) throw new IllegalArgumentException("Layout zone y must be zero or positive");
    }

    private void validateSize(Integer width, Integer height) {
        if (width == null || width <= 0) throw new IllegalArgumentException("Layout zone width must be positive");
        if (height == null || height <= 0) throw new IllegalArgumentException("Layout zone height must be positive");
    }

    private void validateType(ZoneType type) {
        if (type == null) throw new IllegalArgumentException("Layout zone type is required");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public ZoneType getType() {
        return type;
    }
}
