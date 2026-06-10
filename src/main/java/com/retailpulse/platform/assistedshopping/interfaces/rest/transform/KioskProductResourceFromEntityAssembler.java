package com.retailpulse.platform.assistedshopping.interfaces.rest.transform;

import com.retailpulse.platform.storefoundation.domain.model.aggregates.Product;
import com.retailpulse.platform.assistedshopping.interfaces.rest.resources.KioskProductResource;

public class KioskProductResourceFromEntityAssembler {
    public static KioskProductResource toResource(Product product) { return new KioskProductResource(product.getId(), product.getName(), product.getCategory(), product.getPrice(), product.getZoneId()); }
}
