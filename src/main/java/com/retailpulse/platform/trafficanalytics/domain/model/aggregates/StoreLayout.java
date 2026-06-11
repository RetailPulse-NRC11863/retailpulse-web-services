package com.retailpulse.platform.trafficanalytics.domain.model.aggregates;

public class StoreLayout {
    private String id;
    private String name;
    private String storeName;
    private Integer width;
    private Integer height;
    private Boolean active;

    public StoreLayout() {
    }

    public StoreLayout(String id, String name, String storeName, Integer width, Integer height, Boolean active) {
        validateId(id);
        validateName(name);
        validateStoreName(storeName);
        validateSize(width, height);
        this.id = id;
        this.name = name;
        this.storeName = storeName;
        this.width = width;
        this.height = height;
        this.active = active != null && active;
    }

    public void update(String name, String storeName, Integer width, Integer height, Boolean active) {
        validateName(name);
        validateStoreName(storeName);
        validateSize(width, height);
        this.name = name;
        this.storeName = storeName;
        this.width = width;
        this.height = height;
        this.active = active != null && active;
    }

    private void validateId(String id) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Store layout id is required");
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Store layout name is required");
    }

    private void validateStoreName(String storeName) {
        if (storeName == null || storeName.isBlank()) throw new IllegalArgumentException("Store name is required");
    }

    private void validateSize(Integer width, Integer height) {
        if (width == null || width <= 0) throw new IllegalArgumentException("Store layout width must be positive");
        if (height == null || height <= 0) throw new IllegalArgumentException("Store layout height must be positive");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStoreName() {
        return storeName;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public Boolean getActive() {
        return active;
    }
}
