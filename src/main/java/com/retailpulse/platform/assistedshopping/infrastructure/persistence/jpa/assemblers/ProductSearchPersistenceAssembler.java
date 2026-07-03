package com.retailpulse.platform.assistedshopping.infrastructure.persistence.jpa.assemblers;

import com.retailpulse.platform.assistedshopping.domain.model.entities.ProductSearch;
import com.retailpulse.platform.assistedshopping.infrastructure.persistence.jpa.entities.ProductSearchPersistenceEntity;

public class ProductSearchPersistenceAssembler {
    public ProductSearch toDomain(ProductSearchPersistenceEntity entity) {
        return new ProductSearch(entity.getId(), entity.getSessionId(), entity.getQuery(), entity.getProductId(), entity.getStatus(), entity.getAction());
    }

    public ProductSearchPersistenceEntity toEntity(ProductSearch search) {
        ProductSearchPersistenceEntity entity = new ProductSearchPersistenceEntity();
        entity.setId(search.getId());
        entity.setSessionId(search.getSessionId());
        entity.setQuery(search.getQuery());
        entity.setProductId(search.getProductId());
        entity.setStatus(search.getStatus());
        entity.setAction(search.getAction());
        return entity;
    }
}
