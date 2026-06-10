package com.retailpulse.platform.inventoryintelligence.interfaces.rest.transform;

import com.retailpulse.platform.inventoryintelligence.domain.model.commands.UpdateInventoryStockCommand;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.resources.UpdateInventoryStockResource;

public class UpdateInventoryStockCommandFromResourceAssembler {
    public static UpdateInventoryStockCommand toCommand(Long productId, UpdateInventoryStockResource resource) { return new UpdateInventoryStockCommand(productId, resource.availableStock()); }
}
