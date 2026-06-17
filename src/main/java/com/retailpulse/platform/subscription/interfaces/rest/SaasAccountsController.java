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
@RequestMapping({"/api/v1/subscription/accounts", "/api/v1/accounts"})
@Tag(name = "SaaS Accounts")
public class SaasAccountsController {
    private final SaasAccountCommandService commandService;
    private final SaasAccountQueryService queryService;
    public SaasAccountsController(SaasAccountCommandService commandService, SaasAccountQueryService queryService) { this.commandService = commandService; this.queryService = queryService; }
    @GetMapping("/current") @Operation(summary = "Get current SaaS account")
    public ResponseEntity<SaasAccountResource> current() { return queryService.getCurrent().map(SaasAccountResourceFromEntityAssembler::toResource).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @GetMapping("/current-plan") @Operation(summary = "Get current simulated SaaS plan")
    public ResponseEntity<SaasAccountResource> currentPlan() { return current(); }
    @GetMapping("/{accountId}") @Operation(summary = "Get SaaS account by id")
    public ResponseEntity<SaasAccountResource> byId(@PathVariable Long accountId) { return queryService.getById(accountId).map(SaasAccountResourceFromEntityAssembler::toResource).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @PostMapping @Operation(summary = "Create SaaS account")
    public SaasAccountResource create(@Valid @RequestBody CreateSaasAccountResource r) { return SaasAccountResourceFromEntityAssembler.toResource(commandService.handle(CreateSaasAccountCommandFromResourceAssembler.toCommand(r))); }
    @PatchMapping("/{accountId}/change-plan") @Operation(summary = "Change subscription plan")
    public SaasAccountResource changePlan(@PathVariable Long accountId, @Valid @RequestBody ChangeSubscriptionPlanResource r) { return SaasAccountResourceFromEntityAssembler.toResource(commandService.handle(ChangeSubscriptionPlanCommandFromResourceAssembler.toCommand(accountId, r))); }
    @PatchMapping("/current-plan") @Operation(summary = "Change current simulated SaaS plan")
    public ResponseEntity<SaasAccountResource> patchCurrentPlan(@Valid @RequestBody ChangeSubscriptionPlanResource r) { return changeCurrentPlan(r); }
    @PutMapping("/current-plan") @Operation(summary = "Replace current simulated SaaS plan")
    public ResponseEntity<SaasAccountResource> putCurrentPlan(@Valid @RequestBody ChangeSubscriptionPlanResource r) { return changeCurrentPlan(r); }

    private ResponseEntity<SaasAccountResource> changeCurrentPlan(ChangeSubscriptionPlanResource resource) {
        return queryService.getCurrent()
                .map(account -> ChangeSubscriptionPlanCommandFromResourceAssembler.toCommand(account.getId(), resource))
                .map(commandService::handle)
                .map(SaasAccountResourceFromEntityAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
