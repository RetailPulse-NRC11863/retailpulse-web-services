package com.retailpulse.platform.inventoryintelligence.infrastructure.persistence.jpa.assemblers;

import com.retailpulse.platform.inventoryintelligence.domain.model.aggregates.Product;
import com.retailpulse.platform.inventoryintelligence.infrastructure.persistence.jpa.entities.ProductPersistenceEntity;

public class ProductPersistenceAssembler {
    public static Product toDomainFromPersistence(ProductPersistenceEntity entity) {
        return new Product(
            entity.getId(),
            entity.getName(),
            entity.getCategory(),
            entity.getPrice(),
            entity.getStock(),
            entity.getZoneName(),
            entity.getShelfReference(),
            entity.getPromotion()
        );
    }

    public static ProductPersistenceEntity toPersistenceFromDomain(Product product) {
        ProductPersistenceEntity entity = new ProductPersistenceEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setCategory(product.getCategory());
        entity.setPrice(product.getPrice());
        entity.setStock(product.getStock());
        entity.setZoneName(product.getZoneName());
        entity.setShelfReference(product.getShelfReference());
        entity.setPromotion(product.getPromotion());
        return entity;
    }
}
