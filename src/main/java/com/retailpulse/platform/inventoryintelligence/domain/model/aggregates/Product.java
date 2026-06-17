package com.retailpulse.platform.inventoryintelligence.domain.model.aggregates;

import java.math.BigDecimal;

public class Product {
    private String id;
    private String name;
    private String category;
    private BigDecimal price;
    private Integer stock;
    private String zoneName;
    private String shelfReference;
    private String promotion;

    public Product() {
    }

    public Product(String id, String name, String category, BigDecimal price, Integer stock, String zoneName, String shelfReference, String promotion) {
        validateId(id);
        validateRequiredText(name, "Product name is required");
        validateRequiredText(category, "Product category is required");
        validatePrice(price);
        validateStock(stock);
        validateRequiredText(zoneName, "Product zone name is required");
        validateRequiredText(shelfReference, "Product shelf reference is required");
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.zoneName = zoneName;
        this.shelfReference = shelfReference;
        this.promotion = promotion;
    }

    public void updateInformation(String name, String category, BigDecimal price, Integer stock, String zoneName, String shelfReference, String promotion) {
        validateRequiredText(name, "Product name is required");
        validateRequiredText(category, "Product category is required");
        validatePrice(price);
        validateStock(stock);
        validateRequiredText(zoneName, "Product zone name is required");
        validateRequiredText(shelfReference, "Product shelf reference is required");
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.zoneName = zoneName;
        this.shelfReference = shelfReference;
        this.promotion = promotion;
    }

    public void updateStock(Integer stock) {
        validateStock(stock);
        this.stock = stock;
    }

    public boolean hasStock() {
        return stock != null && stock > 0;
    }

    private void validateId(String id) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Product id is required");
    }

    private void validateRequiredText(String value, String message) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(message);
    }

    private void validatePrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Product price must be zero or positive");
    }

    private void validateStock(Integer stock) {
        if (stock == null || stock < 0) throw new IllegalArgumentException("Product stock must be zero or positive");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public String getZoneName() {
        return zoneName;
    }

    public String getShelfReference() {
        return shelfReference;
    }

    public String getPromotion() {
        return promotion;
    }
}
