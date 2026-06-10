package com.retailpulse.platform.inventoryintelligence.interfaces.rest;

import com.retailpulse.platform.inventoryintelligence.application.commandservices.InventoryItemCommandService;
import com.retailpulse.platform.inventoryintelligence.application.queryservices.InventoryItemQueryService;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.resources.CreateInventoryItemResource;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.resources.InventoryItemResource;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.resources.UpdateInventoryStockResource;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.transform.CreateInventoryItemCommandFromResourceAssembler;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.transform.InventoryItemResourceFromEntityAssembler;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.transform.UpdateInventoryStockCommandFromResourceAssembler;
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
    public InventoryItemsController(InventoryItemCommandService commandService, InventoryItemQueryService queryService) { this.commandService = commandService; this.queryService = queryService; }
    @GetMapping @Operation(summary = "Get all inventory items")
    public List<InventoryItemResource> getAll() { return queryService.handleGetAll().stream().map(InventoryItemResourceFromEntityAssembler::toResource).toList(); }
    @GetMapping("/critical") @Operation(summary = "Get critical inventory items")
    public List<InventoryItemResource> critical() { return queryService.handleGetCritical().stream().map(InventoryItemResourceFromEntityAssembler::toResource).toList(); }
    @GetMapping("/product/{productId}") @Operation(summary = "Get inventory item by product")
    public ResponseEntity<InventoryItemResource> byProduct(@PathVariable Long productId) { return queryService.handleGetByProductId(productId).map(InventoryItemResourceFromEntityAssembler::toResource).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @PostMapping @Operation(summary = "Create inventory item")
    public InventoryItemResource create(@Valid @RequestBody CreateInventoryItemResource resource) { return InventoryItemResourceFromEntityAssembler.toResource(commandService.handle(CreateInventoryItemCommandFromResourceAssembler.toCommand(resource))); }
    @PatchMapping("/{productId}/stock") @Operation(summary = "Update inventory stock")
    public InventoryItemResource updateStock(@PathVariable Long productId, @Valid @RequestBody UpdateInventoryStockResource resource) { return InventoryItemResourceFromEntityAssembler.toResource(commandService.handle(UpdateInventoryStockCommandFromResourceAssembler.toCommand(productId, resource))); }
}
