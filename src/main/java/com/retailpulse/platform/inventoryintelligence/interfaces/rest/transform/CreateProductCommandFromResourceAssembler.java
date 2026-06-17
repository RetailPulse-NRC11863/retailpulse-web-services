package com.retailpulse.platform.inventoryintelligence.interfaces.rest.transform;

import com.retailpulse.platform.inventoryintelligence.domain.model.commands.CreateProductCommand;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.resources.CreateProductResource;

public class CreateProductCommandFromResourceAssembler {
    public static CreateProductCommand toCommand(CreateProductResource resource) {
        return new CreateProductCommand(resource.id(), resource.name(), resource.category(), resource.price(), resource.stock(), resource.zoneName(), resource.shelfReference(), resource.promotion());
    }
}
