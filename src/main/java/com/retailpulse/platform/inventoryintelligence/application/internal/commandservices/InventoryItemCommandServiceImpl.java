package com.retailpulse.platform.inventoryintelligence.application.internal.commandservices;

import com.retailpulse.platform.inventoryintelligence.application.commandservices.InventoryItemCommandService;
import com.retailpulse.platform.inventoryintelligence.domain.model.aggregates.InventoryItem;
import com.retailpulse.platform.inventoryintelligence.domain.model.commands.CreateInventoryItemCommand;
import com.retailpulse.platform.inventoryintelligence.domain.model.commands.UpdateInventoryStockCommand;
import com.retailpulse.platform.inventoryintelligence.domain.model.valueobjects.StockStatus;
import com.retailpulse.platform.inventoryintelligence.domain.repositories.InventoryItemRepository;
import com.retailpulse.platform.storefoundation.domain.model.aggregates.Product;
import com.retailpulse.platform.storefoundation.domain.repositories.ProductRepository;
import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalAlert;
import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalTask;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertStatus;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertType;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.PriorityLevel;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.TaskStatus;
import com.retailpulse.platform.storeoperations.domain.repositories.OperationalAlertRepository;
import com.retailpulse.platform.storeoperations.domain.repositories.OperationalTaskRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryItemCommandServiceImpl implements InventoryItemCommandService {
    private final InventoryItemRepository repository;
    private final ProductRepository productRepository;
    private final OperationalAlertRepository alertRepository;
    private final OperationalTaskRepository taskRepository;

    public InventoryItemCommandServiceImpl(InventoryItemRepository repository, ProductRepository productRepository, OperationalAlertRepository alertRepository, OperationalTaskRepository taskRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.alertRepository = alertRepository;
        this.taskRepository = taskRepository;
    }
    public InventoryItem handle(CreateInventoryItemCommand command) {
        StockStatus status = command.availableStock() <= 0 ? StockStatus.OUT_OF_STOCK : command.availableStock() <= command.criticalThreshold() ? StockStatus.LOW_STOCK : StockStatus.AVAILABLE;
        return repository.save(new InventoryItem(null, command.productId(), command.availableStock(), command.criticalThreshold(), status));
    }
    public InventoryItem handle(UpdateInventoryStockCommand command) {
        InventoryItem item = repository.findByProductId(command.productId()).orElseThrow(() -> new IllegalArgumentException("Inventory item not found"));
        item.updateStock(command.availableStock());
        InventoryItem saved = repository.save(item);
        createOperationalWorkIfCritical(saved);
        return saved;
    }

    private void createOperationalWorkIfCritical(InventoryItem item) {
        if (item.getStatus() == StockStatus.AVAILABLE) return;
        Product product = productRepository.findById(item.getProductId()).orElse(null);
        if (product == null) return;
        boolean activeAlertExists = alertRepository.findActive().stream()
            .anyMatch(alert -> AlertType.STOCK == alert.getType() && item.getProductId().equals(alert.getProductId()));
        if (activeAlertExists) return;
        PriorityLevel priority = item.getStatus() == StockStatus.OUT_OF_STOCK ? PriorityLevel.HIGH : PriorityLevel.MEDIUM;
        String reason = product.getName() + " stock changed to " + item.getAvailableStock() + " units. Critical threshold is " + item.getCriticalThreshold() + ".";
        OperationalAlert alert = alertRepository.save(new OperationalAlert(null, product.getStoreId(), "Stock attention required: " + product.getName(), reason, AlertType.STOCK, AlertStatus.ACTIVE, priority, product.getId(), product.getZoneId(), "INVENTORY_STOCK_POLICY", reason));
        taskRepository.save(new OperationalTask(null, product.getStoreId(), "Restock " + product.getName(), "Move available units from storage to the assigned shelf and update stock when finished.", TaskStatus.PENDING, priority, alert.getId(), product.getId(), product.getZoneId(), "INVENTORY_STOCK_POLICY", reason));
    }
}
