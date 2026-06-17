package com.retailpulse.platform.subscription.interfaces.rest;

import com.retailpulse.platform.subscription.application.commandservices.SubscriptionPlanCommandService;
import com.retailpulse.platform.subscription.application.queryservices.SubscriptionPlanQueryService;
import com.retailpulse.platform.subscription.interfaces.rest.resources.*;
import com.retailpulse.platform.subscription.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping({"/api/v1/subscription/plans", "/api/v1/subscription-plans"})
@Tag(name = "Subscription Plans")
public class SubscriptionPlansController {
    private final SubscriptionPlanCommandService commandService;
    private final SubscriptionPlanQueryService queryService;
    public SubscriptionPlansController(SubscriptionPlanCommandService commandService, SubscriptionPlanQueryService queryService) { this.commandService = commandService; this.queryService = queryService; }
    @GetMapping @Operation(summary = "Get all subscription plans")
    public List<SubscriptionPlanResource> all() { return queryService.getAll().stream().map(SubscriptionPlanResourceFromEntityAssembler::toResource).toList(); }
    @GetMapping("/{planId}") @Operation(summary = "Get subscription plan by id")
    public ResponseEntity<SubscriptionPlanResource> byId(@PathVariable Long planId) { return queryService.getById(planId).map(SubscriptionPlanResourceFromEntityAssembler::toResource).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @PostMapping @Operation(summary = "Create subscription plan")
    public SubscriptionPlanResource create(@Valid @RequestBody CreateSubscriptionPlanResource r) { return SubscriptionPlanResourceFromEntityAssembler.toResource(commandService.handle(CreateSubscriptionPlanCommandFromResourceAssembler.toCommand(r))); }
}
