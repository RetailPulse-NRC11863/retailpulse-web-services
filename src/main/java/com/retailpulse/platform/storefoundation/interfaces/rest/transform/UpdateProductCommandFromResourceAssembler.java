package com.retailpulse.platform.storefoundation.interfaces.rest.transform;

import com.retailpulse.platform.storefoundation.domain.model.commands.UpdateProductCommand;
import com.retailpulse.platform.storefoundation.domain.model.valueobjects.ProductLocation;
import com.retailpulse.platform.storefoundation.interfaces.rest.resources.UpdateProductResource;

public class UpdateProductCommandFromResourceAssembler {
    public static UpdateProductCommand toCommand(Long productId, UpdateProductResource resource) { return new UpdateProductCommand(productId, resource.name(), resource.category(), resource.description(), resource.price(), resource.status(), resource.zoneId(), new ProductLocation(resource.aisle(), resource.shelf(), resource.displayReference())); }
}
