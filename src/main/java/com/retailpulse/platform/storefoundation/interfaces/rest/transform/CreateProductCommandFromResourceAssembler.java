package com.retailpulse.platform.storefoundation.interfaces.rest.transform;

import com.retailpulse.platform.storefoundation.domain.model.commands.CreateProductCommand;
import com.retailpulse.platform.storefoundation.domain.model.valueobjects.ProductLocation;
import com.retailpulse.platform.storefoundation.interfaces.rest.resources.CreateProductResource;

public class CreateProductCommandFromResourceAssembler {
    public static CreateProductCommand toCommand(CreateProductResource resource) { return new CreateProductCommand(resource.storeId(), resource.name(), resource.sku(), resource.category(), resource.description(), resource.price(), resource.zoneId(), new ProductLocation(resource.aisle(), resource.shelf(), resource.displayReference())); }
}
