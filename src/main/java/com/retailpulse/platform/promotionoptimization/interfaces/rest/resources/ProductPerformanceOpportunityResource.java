package com.retailpulse.platform.promotionoptimization.interfaces.rest.resources;

public record ProductPerformanceOpportunityResource(
    Long productId,
    String productName,
    Long zoneId,
    String zoneName,
    Integer interactions,
    Integer conversions,
    Double conversionRate,
    Integer availableStock,
    String stockStatus,
    String status,
    String reason,
    Long recommendationId,
    String recommendationTitle,
    String priority
) {
}
