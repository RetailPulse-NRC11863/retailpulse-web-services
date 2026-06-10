package com.retailpulse.platform.assistedshopping.interfaces.rest;

import com.retailpulse.platform.assistedshopping.application.commandservices.KioskSessionCommandService;
import com.retailpulse.platform.assistedshopping.application.queryservices.KioskSessionQueryService;
import com.retailpulse.platform.assistedshopping.interfaces.rest.resources.*;
import com.retailpulse.platform.assistedshopping.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/kiosk")
@Tag(name = "Kiosk")
public class KioskController {
    private final KioskSessionCommandService commandService;
    private final KioskSessionQueryService queryService;
    public KioskController(KioskSessionCommandService commandService, KioskSessionQueryService queryService) { this.commandService = commandService; this.queryService = queryService; }
    @GetMapping("/products/search") @Operation(summary = "Search kiosk products")
    public List<KioskProductResource> search(@RequestParam String query) { return queryService.searchProducts(query).stream().map(KioskProductResourceFromEntityAssembler::toResource).toList(); }
    @GetMapping("/products/{productId}") @Operation(summary = "Get kiosk product by id")
    public ResponseEntity<KioskProductResource> product(@PathVariable Long productId) { return queryService.getProductById(productId).map(KioskProductResourceFromEntityAssembler::toResource).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @PostMapping("/sessions") @Operation(summary = "Start kiosk session")
    public KioskSessionResource start(@Valid @RequestBody StartKioskSessionResource resource) { return KioskSessionResourceFromEntityAssembler.toResource(commandService.handle(StartKioskSessionCommandFromResourceAssembler.toCommand(resource))); }
    @PostMapping("/sessions/{sessionId}/searches") @Operation(summary = "Register product search")
    public void registerSearch(@PathVariable Long sessionId, @Valid @RequestBody RegisterProductSearchResource resource) { commandService.handle(RegisterProductSearchCommandFromResourceAssembler.toCommand(sessionId, resource)); }
    @GetMapping("/sessions/{sessionId}") @Operation(summary = "Get kiosk session")
    public ResponseEntity<KioskSessionResource> session(@PathVariable Long sessionId) { return queryService.getSessionById(sessionId).map(KioskSessionResourceFromEntityAssembler::toResource).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
}
