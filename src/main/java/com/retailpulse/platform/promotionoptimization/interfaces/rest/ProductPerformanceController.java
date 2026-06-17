package com.retailpulse.platform.promotionoptimization.interfaces.rest;

import com.retailpulse.platform.promotionoptimization.application.queryservices.PromotionRecommendationQueryService;
import com.retailpulse.platform.promotionoptimization.interfaces.rest.resources.ProductPerformanceResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-performance")
@Tag(name = "Product Performance")
public class ProductPerformanceController {
    private final PromotionRecommendationQueryService queryService;

    public ProductPerformanceController(PromotionRecommendationQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    @Operation(summary = "Get low-conversion products with commercial recommendations")
    public List<ProductPerformanceResource> all() {
        return queryService.getProductOpportunities().stream()
                .map(recommendation -> new ProductPerformanceResource(
                        recommendation.getProductId(),
                        recommendation.getId(),
                        recommendation.getTitle(),
                        recommendation.getDescription(),
                        recommendation.getType(),
                        recommendation.getStatus(),
                        recommendation.getPriority()))
                .toList();
    }
}
