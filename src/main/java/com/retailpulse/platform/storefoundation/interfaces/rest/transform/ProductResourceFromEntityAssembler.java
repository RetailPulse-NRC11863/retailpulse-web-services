package com.retailpulse.platform.storefoundation.interfaces.rest.transform;

import com.retailpulse.platform.storefoundation.domain.model.aggregates.Product;
import com.retailpulse.platform.storefoundation.domain.model.valueobjects.ProductLocation;
import com.retailpulse.platform.storefoundation.interfaces.rest.resources.ProductResource;

public class ProductResourceFromEntityAssembler {
    public static ProductResource toResource(Product product) { ProductLocation l = product.getLocation(); return new ProductResource(product.getId(), product.getStoreId(), product.getName(), product.getSku(), product.getCategory(), product.getDescription(), product.getPrice(), product.getStatus(), product.getZoneId(), l == null ? null : l.aisle(), l == null ? null : l.shelf(), l == null ? null : l.displayReference()); }
}
