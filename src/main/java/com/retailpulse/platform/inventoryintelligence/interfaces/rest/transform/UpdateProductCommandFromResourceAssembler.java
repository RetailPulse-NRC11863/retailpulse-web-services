package com.retailpulse.platform.inventoryintelligence.interfaces.rest.transform;

import com.retailpulse.platform.inventoryintelligence.domain.model.commands.UpdateProductCommand;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.resources.UpdateProductResource;

public class UpdateProductCommandFromResourceAssembler {
    public static UpdateProductCommand toCommand(String productId, UpdateProductResource resource) {
        return new UpdateProductCommand(productId, resource.name(), resource.category(), resource.price(), resource.stock(), resource.zoneName(), resource.shelfReference(), resource.promotion());
    }
}
