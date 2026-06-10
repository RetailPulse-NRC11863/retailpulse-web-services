package com.retailpulse.platform.inventoryintelligence.application.commandservices;

import com.retailpulse.platform.inventoryintelligence.domain.model.aggregates.InventoryItem;
import com.retailpulse.platform.inventoryintelligence.domain.model.commands.CreateInventoryItemCommand;
import com.retailpulse.platform.inventoryintelligence.domain.model.commands.UpdateInventoryStockCommand;

public interface InventoryItemCommandService {
    InventoryItem handle(CreateInventoryItemCommand command);
    InventoryItem handle(UpdateInventoryStockCommand command);
}
