package com.retailpulse.platform.promotionoptimization.application.commandservices;

import com.retailpulse.platform.promotionoptimization.domain.model.aggregates.PromotionRecommendation;
import com.retailpulse.platform.promotionoptimization.domain.model.commands.CreatePromotionRecommendationCommand;
import com.retailpulse.platform.promotionoptimization.domain.model.commands.ApplyPromotionRecommendationCommand;

public interface PromotionRecommendationCommandService {
    PromotionRecommendation handle(CreatePromotionRecommendationCommand command);
    PromotionRecommendation handle(ApplyPromotionRecommendationCommand command);
}
