package com.retailpulse.platform.storefoundation.interfaces.rest;

import com.retailpulse.platform.storefoundation.application.commandservices.StoreCommandService;
import com.retailpulse.platform.storefoundation.application.queryservices.StoreQueryService;
import com.retailpulse.platform.storefoundation.interfaces.rest.resources.CreateStoreResource;
import com.retailpulse.platform.storefoundation.interfaces.rest.resources.StoreResource;
import com.retailpulse.platform.storefoundation.interfaces.rest.transform.CreateStoreCommandFromResourceAssembler;
import com.retailpulse.platform.storefoundation.interfaces.rest.transform.StoreResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/stores")
@Tag(name = "Stores")
public class StoresController {
    private final StoreCommandService commandService;
    private final StoreQueryService queryService;
    public StoresController(StoreCommandService commandService, StoreQueryService queryService) { this.commandService = commandService; this.queryService = queryService; }
    @GetMapping @Operation(summary = "Get all stores")
    public List<StoreResource> getAll() { return queryService.handleGetAll().stream().map(StoreResourceFromEntityAssembler::toResource).toList(); }
    @GetMapping("/{storeId}") @Operation(summary = "Get store by id")
    public ResponseEntity<StoreResource> getById(@PathVariable Long storeId) { return queryService.handleGetById(storeId).map(StoreResourceFromEntityAssembler::toResource).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @PostMapping @Operation(summary = "Create store")
    public ResponseEntity<StoreResource> create(@Valid @RequestBody CreateStoreResource resource) { return ResponseEntity.ok(StoreResourceFromEntityAssembler.toResource(commandService.handle(CreateStoreCommandFromResourceAssembler.toCommand(resource)))); }
}
