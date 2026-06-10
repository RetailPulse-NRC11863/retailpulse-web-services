package com.retailpulse.platform.storefoundation.infrastructure.persistence.jpa.assemblers;

import com.retailpulse.platform.storefoundation.domain.model.aggregates.Product;
import com.retailpulse.platform.storefoundation.domain.model.valueobjects.ProductLocation;
import com.retailpulse.platform.storefoundation.infrastructure.persistence.jpa.entities.ProductPersistenceEntity;

public class ProductPersistenceAssembler {
    public Product toDomain(ProductPersistenceEntity entity) {
        ProductLocation location = new ProductLocation(entity.getAisle(), entity.getShelf(), entity.getDisplayReference());
        return new Product(entity.getId(), entity.getStoreId(), entity.getName(), entity.getSku(), entity.getCategory(), entity.getDescription(), entity.getPrice(), entity.getStatus(), entity.getZoneId(), location);
    }
    public ProductPersistenceEntity toEntity(Product product) {
        ProductPersistenceEntity entity = new ProductPersistenceEntity();
        entity.setId(product.getId());
        entity.setStoreId(product.getStoreId());
        entity.setName(product.getName());
        entity.setSku(product.getSku());
        entity.setCategory(product.getCategory());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setStatus(product.getStatus());
        entity.setZoneId(product.getZoneId());
        if (product.getLocation() != null) {
            entity.setAisle(product.getLocation().aisle());
            entity.setShelf(product.getLocation().shelf());
            entity.setDisplayReference(product.getLocation().displayReference());
        }
        return entity;
    }
}
