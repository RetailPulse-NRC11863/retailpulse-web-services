package com.retailpulse.platform.promotionoptimization.interfaces.rest.transform;

import com.retailpulse.platform.promotionoptimization.domain.model.commands.CreatePromotionRecommendationCommand;
import com.retailpulse.platform.promotionoptimization.interfaces.rest.resources.CreatePromotionRecommendationResource;

public class CreatePromotionRecommendationCommandFromResourceAssembler {
    public static CreatePromotionRecommendationCommand toCommand(CreatePromotionRecommendationResource r) { return new CreatePromotionRecommendationCommand(r.productId(), r.title(), r.description(), r.type(), r.priority()); }
}
