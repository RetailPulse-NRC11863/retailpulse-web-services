package com.retailpulse.platform.inventoryintelligence.interfaces.rest;

import com.retailpulse.platform.inventoryintelligence.application.commandservices.InventoryItemCommandService;
import com.retailpulse.platform.inventoryintelligence.application.queryservices.InventoryItemQueryService;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.resources.CreateInventoryItemResource;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.resources.InventoryItemResource;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.resources.UpdateInventoryStockResource;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.transform.CreateInventoryItemCommandFromResourceAssembler;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.transform.InventoryItemResourceFromEntityAssembler;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.transform.UpdateInventoryStockCommandFromResourceAssembler;
import com.retailpulse.platform.inventoryintelligence.domain.model.aggregates.InventoryItem;
import com.retailpulse.platform.storefoundation.domain.repositories.ProductRepository;
import com.retailpulse.platform.subscription.domain.repositories.SaasAccountRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory/items")
@Tag(name = "Inventory Items")
public class InventoryItemsController {
    private final InventoryItemCommandService commandService;
    private final InventoryItemQueryService queryService;
    private final ProductRepository productRepository;
    private final SaasAccountRepository accountRepository;
    public InventoryItemsController(InventoryItemCommandService commandService, InventoryItemQueryService queryService, ProductRepository productRepository, SaasAccountRepository accountRepository) { this.commandService = commandService; this.queryService = queryService; this.productRepository = productRepository; this.accountRepository = accountRepository; }
    @GetMapping @Operation(summary = "Get all inventory items")
    public List<InventoryItemResource> getAll(@RequestHeader(value = "X-Owner-Email", required = false) String ownerEmail) { return queryService.handleGetAll().stream().filter(item -> belongsToCurrentStore(item, ownerEmail)).map(InventoryItemResourceFromEntityAssembler::toResource).toList(); }
    @GetMapping("/critical") @Operation(summary = "Get critical inventory items")
    public List<InventoryItemResource> critical(@RequestHeader(value = "X-Owner-Email", required = false) String ownerEmail) { return queryService.handleGetCritical().stream().filter(item -> belongsToCurrentStore(item, ownerEmail)).map(InventoryItemResourceFromEntityAssembler::toResource).toList(); }
    @GetMapping("/product/{productId}") @Operation(summary = "Get inventory item by product")
    public ResponseEntity<InventoryItemResource> byProduct(@PathVariable Long productId) { return queryService.handleGetByProductId(productId).map(InventoryItemResourceFromEntityAssembler::toResource).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @PostMapping @Operation(summary = "Create inventory item")
    public InventoryItemResource create(@Valid @RequestBody CreateInventoryItemResource resource) { return InventoryItemResourceFromEntityAssembler.toResource(commandService.handle(CreateInventoryItemCommandFromResourceAssembler.toCommand(resource))); }
    @PatchMapping("/{productId}/stock") @Operation(summary = "Update inventory stock")
    public InventoryItemResource updateStock(@PathVariable Long productId, @Valid @RequestBody UpdateInventoryStockResource resource) { return InventoryItemResourceFromEntityAssembler.toResource(commandService.handle(UpdateInventoryStockCommandFromResourceAssembler.toCommand(productId, resource))); }

    private boolean belongsToCurrentStore(InventoryItem item, String ownerEmail) {
        Long storeId = currentStoreId(ownerEmail);
        return storeId == null || productRepository.findById(item.getProductId())
            .filter(product -> storeId.equals(product.getStoreId()))
            .isPresent();
    }

    private Long currentStoreId(String ownerEmail) {
        if (ownerEmail != null && !ownerEmail.isBlank()) {
            return accountRepository.findByOwnerEmail(ownerEmail).map(account -> account.getStoreId()).orElse(null);
        }
        return null;
    }
}
