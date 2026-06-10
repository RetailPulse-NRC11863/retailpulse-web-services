package com.retailpulse.platform.promotionoptimization.interfaces.rest.transform;

import com.retailpulse.platform.promotionoptimization.domain.model.commands.ApplyPromotionRecommendationCommand;

public class ApplyPromotionRecommendationCommandFromResourceAssembler {
    public static ApplyPromotionRecommendationCommand toCommand(Long recommendationId) { return new ApplyPromotionRecommendationCommand(recommendationId); }
}
