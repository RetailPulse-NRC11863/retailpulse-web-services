package com.retailpulse.platform.promotionoptimization.domain.model.commands;

import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.RecommendationType;
import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.CommercialPriority;

public record CreatePromotionRecommendationCommand(Long productId, String title, String description, RecommendationType type, CommercialPriority priority) {
}
