package com.retailpulse.platform.inventoryintelligence.interfaces.rest.transform;

import com.retailpulse.platform.inventoryintelligence.domain.model.commands.CreateInventoryItemCommand;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.resources.CreateInventoryItemResource;

public class CreateInventoryItemCommandFromResourceAssembler {
    public static CreateInventoryItemCommand toCommand(CreateInventoryItemResource resource) { return new CreateInventoryItemCommand(resource.productId(), resource.availableStock(), resource.criticalThreshold()); }
}
