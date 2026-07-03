package com.retailpulse.platform.storeoperations.interfaces.rest;

import com.retailpulse.platform.storeoperations.application.commandservices.OperationalAlertCommandService;
import com.retailpulse.platform.storeoperations.application.queryservices.OperationalAlertQueryService;
import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalAlert;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.*;
import com.retailpulse.platform.storeoperations.interfaces.rest.transform.*;
import com.retailpulse.platform.subscription.domain.repositories.SaasAccountRepository;
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
    private final SaasAccountRepository accountRepository;
    public OperationalAlertsController(OperationalAlertCommandService commandService, OperationalAlertQueryService queryService, SaasAccountRepository accountRepository) { this.commandService = commandService; this.queryService = queryService; this.accountRepository = accountRepository; }
    @GetMapping @Operation(summary = "Get all operational alerts")
    public List<OperationalAlertResource> all(@RequestHeader(value = "X-Owner-Email", required = false) String ownerEmail) { return queryService.getAll().stream().filter(alert -> belongsToCurrentStore(alert, ownerEmail)).map(OperationalAlertResourceFromEntityAssembler::toResource).toList(); }
    @GetMapping("/active") @Operation(summary = "Get active operational alerts")
    public List<OperationalAlertResource> active(@RequestHeader(value = "X-Owner-Email", required = false) String ownerEmail) { return queryService.getActive().stream().filter(alert -> belongsToCurrentStore(alert, ownerEmail)).map(OperationalAlertResourceFromEntityAssembler::toResource).toList(); }
    @PostMapping @Operation(summary = "Create operational alert")
    public OperationalAlertResource create(@Valid @RequestBody CreateOperationalAlertResource r) { return OperationalAlertResourceFromEntityAssembler.toResource(commandService.handle(CreateOperationalAlertCommandFromResourceAssembler.toCommand(r))); }
    @PatchMapping("/{alertId}/resolve") @Operation(summary = "Resolve operational alert")
    public OperationalAlertResource resolve(@PathVariable Long alertId) { return OperationalAlertResourceFromEntityAssembler.toResource(commandService.handle(ResolveOperationalAlertCommandFromResourceAssembler.toCommand(alertId))); }

    private boolean belongsToCurrentStore(OperationalAlert alert, String ownerEmail) {
        Long storeId = currentStoreId(ownerEmail);
        boolean traceable = alert.getProductId() != null || alert.getZoneId() != null || alert.getSource() != null;
        return traceable && (storeId == null || storeId.equals(alert.getStoreId()));
    }

    private Long currentStoreId(String ownerEmail) {
        if (ownerEmail != null && !ownerEmail.isBlank()) {
            return accountRepository.findByOwnerEmail(ownerEmail).map(account -> account.getStoreId()).orElse(null);
        }
        return accountRepository.findFirst().map(account -> account.getStoreId()).orElse(null);
    }
}
