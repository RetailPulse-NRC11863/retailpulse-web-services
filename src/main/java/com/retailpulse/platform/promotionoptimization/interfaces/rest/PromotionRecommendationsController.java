package com.retailpulse.platform.promotionoptimization.interfaces.rest;

import com.retailpulse.platform.promotionoptimization.application.commandservices.PromotionRecommendationCommandService;
import com.retailpulse.platform.promotionoptimization.application.queryservices.PromotionRecommendationQueryService;
import com.retailpulse.platform.assistedshopping.domain.model.entities.ProductSearch;
import com.retailpulse.platform.assistedshopping.domain.repositories.ProductSearchRepository;
import com.retailpulse.platform.inventoryintelligence.domain.model.aggregates.InventoryItem;
import com.retailpulse.platform.inventoryintelligence.domain.repositories.InventoryItemRepository;
import com.retailpulse.platform.promotionoptimization.domain.model.aggregates.PromotionRecommendation;
import com.retailpulse.platform.promotionoptimization.domain.model.commands.CreatePromotionRecommendationCommand;
import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.CommercialPriority;
import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.RecommendationType;
import com.retailpulse.platform.promotionoptimization.interfaces.rest.resources.*;
import com.retailpulse.platform.promotionoptimization.interfaces.rest.transform.*;
import com.retailpulse.platform.storefoundation.domain.model.aggregates.Product;
import com.retailpulse.platform.storefoundation.domain.model.entities.Zone;
import com.retailpulse.platform.storefoundation.domain.repositories.ProductRepository;
import com.retailpulse.platform.storefoundation.domain.repositories.ZoneRepository;
import com.retailpulse.platform.subscription.domain.repositories.SaasAccountRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/promotion-recommendations")
@Tag(name = "Promotion Recommendations")
public class PromotionRecommendationsController {
    private final PromotionRecommendationCommandService commandService;
    private final PromotionRecommendationQueryService queryService;
    private final ProductRepository productRepository;
    private final ZoneRepository zoneRepository;
    private final InventoryItemRepository inventoryItemRepository;
    private final ProductSearchRepository productSearchRepository;
    private final SaasAccountRepository accountRepository;

    public PromotionRecommendationsController(
        PromotionRecommendationCommandService commandService,
        PromotionRecommendationQueryService queryService,
        ProductRepository productRepository,
        ZoneRepository zoneRepository,
        InventoryItemRepository inventoryItemRepository,
        ProductSearchRepository productSearchRepository,
        SaasAccountRepository accountRepository
    ) {
        this.commandService = commandService;
        this.queryService = queryService;
        this.productRepository = productRepository;
        this.zoneRepository = zoneRepository;
        this.inventoryItemRepository = inventoryItemRepository;
        this.productSearchRepository = productSearchRepository;
        this.accountRepository = accountRepository;
    }
    @GetMapping @Operation(summary = "Get all promotion recommendations")
    public List<PromotionRecommendationResource> all(@RequestHeader(value = "X-Owner-Email", required = false) String ownerEmail) {
        synchronizeRuleBasedRecommendations(ownerEmail);
        return queryService.getAll().stream().filter(recommendation -> belongsToCurrentStore(recommendation, ownerEmail)).map(PromotionRecommendationResourceFromEntityAssembler::toResource).toList();
    }
    @GetMapping("/active") @Operation(summary = "Get active promotion recommendations")
    public List<PromotionRecommendationResource> active(@RequestHeader(value = "X-Owner-Email", required = false) String ownerEmail) {
        synchronizeRuleBasedRecommendations(ownerEmail);
        return queryService.getActive().stream().filter(recommendation -> belongsToCurrentStore(recommendation, ownerEmail)).map(PromotionRecommendationResourceFromEntityAssembler::toResource).toList();
    }
    @GetMapping("/product-opportunities") @Operation(summary = "Get product performance opportunities")
    public List<ProductPerformanceOpportunityResource> opportunities(@RequestHeader(value = "X-Owner-Email", required = false) String ownerEmail) {
        synchronizeRuleBasedRecommendations(ownerEmail);
        List<PromotionRecommendation> recommendations = queryService.getAll();
        return productRepository.findAll().stream().filter(product -> belongsToCurrentStore(product, ownerEmail)).map(product -> toOpportunity(product, recommendations)).toList();
    }
    @PostMapping @Operation(summary = "Create promotion recommendation")
    public PromotionRecommendationResource create(@Valid @RequestBody CreatePromotionRecommendationResource r) { return PromotionRecommendationResourceFromEntityAssembler.toResource(commandService.handle(CreatePromotionRecommendationCommandFromResourceAssembler.toCommand(r))); }
    @PatchMapping("/{recommendationId}/apply") @Operation(summary = "Apply promotion recommendation")
    public PromotionRecommendationResource apply(@PathVariable Long recommendationId) { return PromotionRecommendationResourceFromEntityAssembler.toResource(commandService.handle(ApplyPromotionRecommendationCommandFromResourceAssembler.toCommand(recommendationId))); }

    private ProductPerformanceOpportunityResource toOpportunity(Product product) {
        return toOpportunity(product, queryService.getAll());
    }

    private ProductPerformanceOpportunityResource toOpportunity(Product product, List<PromotionRecommendation> recommendations) {
        List<ProductSearch> searches = productSearchRepository.findByProductId(product.getId());
        int interactions = searches.size();
        int conversions = (int) searches.stream().filter(search -> "FOUND".equalsIgnoreCase(search.getAction())).count();
        PromotionRecommendation recommendation = recommendations.stream()
            .filter(r -> product.getId().equals(r.getProductId()))
            .findFirst()
            .orElse(null);
        double conversionRate = interactions == 0 ? 0 : Math.round((conversions * 1000.0 / interactions)) / 10.0;
        InventoryItem item = inventoryItemRepository.findByProductId(product.getId()).orElse(null);
        Zone zone = product.getZoneId() == null ? null : zoneRepository.findById(product.getZoneId()).orElse(null);
        boolean hasInteraction = interactions > 0;
        boolean inventoryRisk = item != null && !"AVAILABLE".equals(item.getStatus().name());
        boolean needsAttention = (hasInteraction && conversionRate < 35.0) || inventoryRisk;
        String reason = opportunityReason(hasInteraction, inventoryRisk, conversionRate);
        return new ProductPerformanceOpportunityResource(
            product.getId(),
            product.getName(),
            product.getZoneId(),
            zone == null ? null : zone.getName(),
            interactions,
            conversions,
            conversionRate,
            item == null ? null : item.getAvailableStock(),
            item == null ? null : item.getStatus().name(),
            needsAttention ? "NEEDS_ATTENTION" : "GOOD",
            reason,
            recommendation == null ? null : recommendation.getId(),
            recommendation == null ? null : recommendation.getTitle(),
            recommendation == null ? null : recommendation.getPriority().name()
        );
    }

    private void synchronizeRuleBasedRecommendations(String ownerEmail) {
        List<PromotionRecommendation> recommendations = new ArrayList<>(queryService.getAll());
        productRepository.findAll().stream()
            .filter(product -> belongsToCurrentStore(product, ownerEmail))
            .forEach(product -> {
                ProductPerformanceOpportunityResource opportunity = toOpportunity(product, recommendations);
                if (!"NEEDS_ATTENTION".equals(opportunity.status())) return;

                boolean alreadyRecommended = recommendations.stream()
                    .anyMatch(recommendation -> product.getId().equals(recommendation.getProductId()) && recommendation.getStatus() != null);
                if (alreadyRecommended) return;

                PromotionRecommendation created = commandService.handle(new CreatePromotionRecommendationCommand(
                    product.getId(),
                    recommendationTitle(product, opportunity),
                    opportunity.reason(),
                    recommendationType(opportunity),
                    recommendationPriority(opportunity)
                ));
                recommendations.add(created);
            });
    }

    private String recommendationTitle(Product product, ProductPerformanceOpportunityResource opportunity) {
        if (isInventoryRisk(opportunity)) return "Replenish " + product.getName();
        if (opportunity.conversionRate() < 20.0) return "Improve conversion for " + product.getName();
        return "Review placement for " + product.getName();
    }

    private RecommendationType recommendationType(ProductPerformanceOpportunityResource opportunity) {
        if (isInventoryRisk(opportunity)) return RecommendationType.STAFF_ASSISTANCE;
        if (opportunity.conversionRate() < 20.0) return RecommendationType.DISCOUNT;
        return RecommendationType.RELOCATION;
    }

    private CommercialPriority recommendationPriority(ProductPerformanceOpportunityResource opportunity) {
        if (isInventoryRisk(opportunity) || opportunity.conversionRate() < 20.0) return CommercialPriority.HIGH;
        if (opportunity.conversionRate() < 35.0) return CommercialPriority.MEDIUM;
        return CommercialPriority.LOW;
    }

    private boolean isInventoryRisk(ProductPerformanceOpportunityResource opportunity) {
        return opportunity.stockStatus() != null && !"AVAILABLE".equals(opportunity.stockStatus());
    }

    private boolean belongsToCurrentStore(PromotionRecommendation recommendation, String ownerEmail) {
        return productRepository.findById(recommendation.getProductId())
            .filter(product -> belongsToCurrentStore(product, ownerEmail))
            .isPresent();
    }

    private boolean belongsToCurrentStore(Product product, String ownerEmail) {
        Long storeId = currentStoreId(ownerEmail);
        return storeId == null || storeId.equals(product.getStoreId());
    }

    private Long currentStoreId(String ownerEmail) {
        if (ownerEmail != null && !ownerEmail.isBlank()) {
            return accountRepository.findByOwnerEmail(ownerEmail).map(account -> account.getStoreId()).orElse(null);
        }
        return accountRepository.findFirst().map(account -> account.getStoreId()).orElse(null);
    }

    private String opportunityReason(boolean hasInteraction, boolean inventoryRisk, double conversionRate) {
        if (inventoryRisk) return "Product requires attention because inventory is low or out of stock.";
        if (!hasInteraction) return "No kiosk interaction has been recorded for this product yet.";
        if (conversionRate < 35.0) return "Product has kiosk interaction but conversion is below the expected threshold.";
        return "Product interaction and conversion are within the expected range.";
    }
}
