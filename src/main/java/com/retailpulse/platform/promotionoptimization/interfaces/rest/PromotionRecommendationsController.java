package com.retailpulse.platform.promotionoptimization.interfaces.rest;

import com.retailpulse.platform.promotionoptimization.application.commandservices.PromotionRecommendationCommandService;
import com.retailpulse.platform.promotionoptimization.application.queryservices.PromotionRecommendationQueryService;
import com.retailpulse.platform.promotionoptimization.interfaces.rest.resources.*;
import com.retailpulse.platform.promotionoptimization.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping({"/api/v1/promotion-recommendations", "/api/v1/recommendations"})
@Tag(name = "Promotion Recommendations")
public class PromotionRecommendationsController {
    private final PromotionRecommendationCommandService commandService;
    private final PromotionRecommendationQueryService queryService;
    public PromotionRecommendationsController(PromotionRecommendationCommandService commandService, PromotionRecommendationQueryService queryService) { this.commandService = commandService; this.queryService = queryService; }
    @GetMapping @Operation(summary = "Get all promotion recommendations")
    public List<PromotionRecommendationResource> all() { return queryService.getAll().stream().map(PromotionRecommendationResourceFromEntityAssembler::toResource).toList(); }
    @GetMapping("/active") @Operation(summary = "Get active promotion recommendations")
    public List<PromotionRecommendationResource> active() { return queryService.getActive().stream().map(PromotionRecommendationResourceFromEntityAssembler::toResource).toList(); }
    @GetMapping("/product-opportunities") @Operation(summary = "Get product performance opportunities")
    public List<PromotionRecommendationResource> opportunities() { return queryService.getProductOpportunities().stream().map(PromotionRecommendationResourceFromEntityAssembler::toResource).toList(); }
    @PostMapping @Operation(summary = "Create promotion recommendation")
    public PromotionRecommendationResource create(@Valid @RequestBody CreatePromotionRecommendationResource r) { return PromotionRecommendationResourceFromEntityAssembler.toResource(commandService.handle(CreatePromotionRecommendationCommandFromResourceAssembler.toCommand(r))); }
    @PatchMapping("/{recommendationId}/apply") @Operation(summary = "Apply promotion recommendation")
    public PromotionRecommendationResource apply(@PathVariable Long recommendationId) { return PromotionRecommendationResourceFromEntityAssembler.toResource(commandService.handle(ApplyPromotionRecommendationCommandFromResourceAssembler.toCommand(recommendationId))); }
}
