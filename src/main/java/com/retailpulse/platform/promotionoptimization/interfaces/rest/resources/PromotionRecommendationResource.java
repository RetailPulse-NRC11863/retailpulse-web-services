package com.retailpulse.platform.promotionoptimization.interfaces.rest.resources;

import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.CommercialPriority;
import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.RecommendationStatus;
import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.RecommendationType;

public record PromotionRecommendationResource(Long id, Long productId, String title, String description, RecommendationType type, RecommendationStatus status, CommercialPriority priority) {
}
