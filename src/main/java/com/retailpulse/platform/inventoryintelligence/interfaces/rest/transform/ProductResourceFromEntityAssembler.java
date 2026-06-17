package com.retailpulse.platform.inventoryintelligence.interfaces.rest.transform;

import com.retailpulse.platform.inventoryintelligence.domain.model.aggregates.Product;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.resources.ProductResource;

public class ProductResourceFromEntityAssembler {
    public static ProductResource toResource(Product product) {
        return new ProductResource(
            product.getId(),
            product.getName(),
            product.getCategory(),
            product.getPrice(),
            product.getStock(),
            product.getZoneName(),
            product.getShelfReference(),
            product.getPromotion()
        );
    }
}
