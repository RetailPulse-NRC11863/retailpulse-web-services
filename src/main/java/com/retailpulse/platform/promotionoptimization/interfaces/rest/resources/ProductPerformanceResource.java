package com.retailpulse.platform.promotionoptimization.interfaces.rest.resources;

import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.CommercialPriority;
import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.RecommendationStatus;
import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.RecommendationType;

public record ProductPerformanceResource(
        Long productId,
        Long recommendationId,
        String recommendationTitle,
        String recommendationDescription,
        RecommendationType recommendationType,
        RecommendationStatus recommendationStatus,
        CommercialPriority priority
) {
}
