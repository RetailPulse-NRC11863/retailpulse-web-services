package com.retailpulse.platform.storefoundation.interfaces.rest;

import com.retailpulse.platform.storefoundation.application.commandservices.ZoneCommandService;
import com.retailpulse.platform.storefoundation.application.queryservices.ZoneQueryService;
import com.retailpulse.platform.storefoundation.interfaces.rest.resources.CreateZoneResource;
import com.retailpulse.platform.storefoundation.interfaces.rest.resources.UpdateZoneResource;
import com.retailpulse.platform.storefoundation.interfaces.rest.resources.ZoneResource;
import com.retailpulse.platform.storefoundation.interfaces.rest.transform.CreateZoneCommandFromResourceAssembler;
import com.retailpulse.platform.storefoundation.interfaces.rest.transform.UpdateZoneCommandFromResourceAssembler;
import com.retailpulse.platform.storefoundation.interfaces.rest.transform.ZoneResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/store-foundation/zones")
@Tag(name = "Store Foundation - Zones")
public class ZonesController {
    private final ZoneCommandService commandService;
    private final ZoneQueryService queryService;
    public ZonesController(ZoneCommandService commandService, ZoneQueryService queryService) { this.commandService = commandService; this.queryService = queryService; }
    @GetMapping @Operation(summary = "Get all zones")
    public List<ZoneResource> getAll() { return queryService.handleGetAll().stream().map(ZoneResourceFromEntityAssembler::toResource).toList(); }
    @GetMapping("/{zoneId}") @Operation(summary = "Get zone by id")
    public ResponseEntity<ZoneResource> getById(@PathVariable Long zoneId) { return queryService.handleGetById(zoneId).map(ZoneResourceFromEntityAssembler::toResource).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @PostMapping @Operation(summary = "Create zone")
    public ResponseEntity<ZoneResource> create(@Valid @RequestBody CreateZoneResource resource) { return ResponseEntity.ok(ZoneResourceFromEntityAssembler.toResource(commandService.handle(CreateZoneCommandFromResourceAssembler.toCommand(resource)))); }
    @PutMapping("/{zoneId}") @Operation(summary = "Update zone")
    public ZoneResource update(@PathVariable Long zoneId, @Valid @RequestBody UpdateZoneResource resource) { return ZoneResourceFromEntityAssembler.toResource(commandService.handle(UpdateZoneCommandFromResourceAssembler.toCommand(zoneId, resource))); }
    @DeleteMapping("/{zoneId}") @Operation(summary = "Delete zone")
    public ResponseEntity<Void> delete(@PathVariable Long zoneId) { commandService.delete(zoneId); return ResponseEntity.noContent().build(); }
}
