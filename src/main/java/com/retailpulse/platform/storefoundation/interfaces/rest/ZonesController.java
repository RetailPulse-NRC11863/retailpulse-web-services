package com.retailpulse.platform.storefoundation.interfaces.rest;

import com.retailpulse.platform.storefoundation.application.commandservices.ZoneCommandService;
import com.retailpulse.platform.storefoundation.application.queryservices.ZoneQueryService;
import com.retailpulse.platform.storefoundation.interfaces.rest.resources.CreateZoneResource;
import com.retailpulse.platform.storefoundation.interfaces.rest.resources.UpdateZoneResource;
import com.retailpulse.platform.storefoundation.interfaces.rest.resources.ZoneResource;
import com.retailpulse.platform.storefoundation.interfaces.rest.transform.CreateZoneCommandFromResourceAssembler;
import com.retailpulse.platform.storefoundation.interfaces.rest.transform.UpdateZoneCommandFromResourceAssembler;
import com.retailpulse.platform.storefoundation.interfaces.rest.transform.ZoneResourceFromEntityAssembler;
import com.retailpulse.platform.subscription.domain.repositories.SaasAccountRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/zones")
@Tag(name = "Zones")
public class ZonesController {
    private final ZoneCommandService commandService;
    private final ZoneQueryService queryService;
    private final SaasAccountRepository accountRepository;
    public ZonesController(ZoneCommandService commandService, ZoneQueryService queryService, SaasAccountRepository accountRepository) { this.commandService = commandService; this.queryService = queryService; this.accountRepository = accountRepository; }
    @GetMapping @Operation(summary = "Get all zones")
    public List<ZoneResource> getAll(@RequestHeader(value = "X-Owner-Email", required = false) String ownerEmail, @RequestParam(required = false) Long storeId) {
        Long selectedStoreId = storeId != null ? storeId : currentStoreId(ownerEmail);
        return queryService.handleGetAll().stream()
            .filter(zone -> selectedStoreId == null || selectedStoreId.equals(zone.getStoreId()))
            .map(ZoneResourceFromEntityAssembler::toResource).toList();
    }
    @GetMapping("/{zoneId}") @Operation(summary = "Get zone by id")
    public ResponseEntity<ZoneResource> getById(@PathVariable Long zoneId) { return queryService.handleGetById(zoneId).map(ZoneResourceFromEntityAssembler::toResource).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @PostMapping @Operation(summary = "Create zone")
    public ResponseEntity<ZoneResource> create(@Valid @RequestBody CreateZoneResource resource) { return ResponseEntity.ok(ZoneResourceFromEntityAssembler.toResource(commandService.handle(CreateZoneCommandFromResourceAssembler.toCommand(resource)))); }
    @PutMapping("/{zoneId}") @Operation(summary = "Update zone")
    public ZoneResource update(@PathVariable Long zoneId, @Valid @RequestBody UpdateZoneResource resource) { return ZoneResourceFromEntityAssembler.toResource(commandService.handle(UpdateZoneCommandFromResourceAssembler.toCommand(zoneId, resource))); }
    @DeleteMapping("/{zoneId}") @Operation(summary = "Delete zone")
    public ResponseEntity<Void> delete(@PathVariable Long zoneId) { commandService.delete(zoneId); return ResponseEntity.noContent().build(); }

    private Long currentStoreId(String ownerEmail) {
        if (ownerEmail != null && !ownerEmail.isBlank()) {
            return accountRepository.findByOwnerEmail(ownerEmail).map(account -> account.getStoreId()).orElse(null);
        }
        return null;
    }
}
