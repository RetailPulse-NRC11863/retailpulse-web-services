package com.retailpulse.platform.subscription.interfaces.rest;

import com.retailpulse.platform.subscription.application.commandservices.SaasAccountCommandService;
import com.retailpulse.platform.subscription.application.queryservices.SaasAccountQueryService;
import com.retailpulse.platform.subscription.interfaces.rest.resources.*;
import com.retailpulse.platform.subscription.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subscription/accounts")
@Tag(name = "SaaS Accounts")
public class SaasAccountsController {
    private final SaasAccountCommandService commandService;
    private final SaasAccountQueryService queryService;
    public SaasAccountsController(SaasAccountCommandService commandService, SaasAccountQueryService queryService) { this.commandService = commandService; this.queryService = queryService; }
    @GetMapping("/current") @Operation(summary = "Get current SaaS account")
    public ResponseEntity<SaasAccountResource> current(@RequestHeader(value = "X-Owner-Email", required = false) String ownerEmail) { return queryService.getCurrent(ownerEmail).map(SaasAccountResourceFromEntityAssembler::toResource).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @GetMapping("/{accountId}") @Operation(summary = "Get SaaS account by id")
    public ResponseEntity<SaasAccountResource> byId(@PathVariable Long accountId) { return queryService.getById(accountId).map(SaasAccountResourceFromEntityAssembler::toResource).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @PostMapping @Operation(summary = "Create SaaS account")
    public SaasAccountResource create(@Valid @RequestBody CreateSaasAccountResource r) { return SaasAccountResourceFromEntityAssembler.toResource(commandService.handle(CreateSaasAccountCommandFromResourceAssembler.toCommand(r))); }
    @PatchMapping("/{accountId}/change-plan") @Operation(summary = "Change subscription plan")
    public SaasAccountResource changePlan(@PathVariable Long accountId, @Valid @RequestBody ChangeSubscriptionPlanResource r) { return SaasAccountResourceFromEntityAssembler.toResource(commandService.handle(ChangeSubscriptionPlanCommandFromResourceAssembler.toCommand(accountId, r))); }
}
