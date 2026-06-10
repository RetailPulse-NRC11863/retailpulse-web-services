package com.retailpulse.platform.storeoperations.interfaces.rest;

import com.retailpulse.platform.storeoperations.application.commandservices.OperationalAlertCommandService;
import com.retailpulse.platform.storeoperations.application.queryservices.OperationalAlertQueryService;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.*;
import com.retailpulse.platform.storeoperations.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/operational-alerts")
@Tag(name = "Operational Alerts")
public class OperationalAlertsController {
    private final OperationalAlertCommandService commandService;
    private final OperationalAlertQueryService queryService;
    public OperationalAlertsController(OperationalAlertCommandService commandService, OperationalAlertQueryService queryService) { this.commandService = commandService; this.queryService = queryService; }
    @GetMapping @Operation(summary = "Get all operational alerts")
    public List<OperationalAlertResource> all() { return queryService.getAll().stream().map(OperationalAlertResourceFromEntityAssembler::toResource).toList(); }
    @GetMapping("/active") @Operation(summary = "Get active operational alerts")
    public List<OperationalAlertResource> active() { return queryService.getActive().stream().map(OperationalAlertResourceFromEntityAssembler::toResource).toList(); }
    @PostMapping @Operation(summary = "Create operational alert")
    public OperationalAlertResource create(@Valid @RequestBody CreateOperationalAlertResource r) { return OperationalAlertResourceFromEntityAssembler.toResource(commandService.handle(CreateOperationalAlertCommandFromResourceAssembler.toCommand(r))); }
    @PatchMapping("/{alertId}/resolve") @Operation(summary = "Resolve operational alert")
    public OperationalAlertResource resolve(@PathVariable Long alertId) { return OperationalAlertResourceFromEntityAssembler.toResource(commandService.handle(ResolveOperationalAlertCommandFromResourceAssembler.toCommand(alertId))); }
}
