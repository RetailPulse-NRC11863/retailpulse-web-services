package com.retailpulse.platform.inventoryintelligence.application.internal.commandservices;

import com.retailpulse.platform.inventoryintelligence.application.commandservices.InventoryItemCommandService;
import com.retailpulse.platform.inventoryintelligence.domain.model.aggregates.InventoryItem;
import com.retailpulse.platform.inventoryintelligence.domain.model.commands.CreateInventoryItemCommand;
import com.retailpulse.platform.inventoryintelligence.domain.model.commands.UpdateInventoryStockCommand;
import com.retailpulse.platform.inventoryintelligence.domain.model.valueobjects.StockStatus;
import com.retailpulse.platform.inventoryintelligence.domain.repositories.InventoryItemRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryItemCommandServiceImpl implements InventoryItemCommandService {
    private final InventoryItemRepository repository;
    public InventoryItemCommandServiceImpl(InventoryItemRepository repository) { this.repository = repository; }
    public InventoryItem handle(CreateInventoryItemCommand command) {
        StockStatus status = command.availableStock() <= 0 ? StockStatus.OUT_OF_STOCK : command.availableStock() <= command.criticalThreshold() ? StockStatus.LOW_STOCK : StockStatus.AVAILABLE;
        return repository.save(new InventoryItem(null, command.productId(), command.availableStock(), command.criticalThreshold(), status));
    }
    public InventoryItem handle(UpdateInventoryStockCommand command) {
        InventoryItem item = repository.findByProductId(command.productId()).orElseThrow(() -> new IllegalArgumentException("Inventory item not found"));
        item.updateStock(command.availableStock());
        return repository.save(item);
    }
}
