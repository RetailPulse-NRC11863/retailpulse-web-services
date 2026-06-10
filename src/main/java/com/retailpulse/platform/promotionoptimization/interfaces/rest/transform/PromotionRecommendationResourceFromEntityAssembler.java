package com.retailpulse.platform.promotionoptimization.interfaces.rest.transform;

import com.retailpulse.platform.promotionoptimization.domain.model.aggregates.PromotionRecommendation;
import com.retailpulse.platform.promotionoptimization.interfaces.rest.resources.PromotionRecommendationResource;

public class PromotionRecommendationResourceFromEntityAssembler {
    public static PromotionRecommendationResource toResource(PromotionRecommendation r) { return new PromotionRecommendationResource(r.getId(), r.getProductId(), r.getTitle(), r.getDescription(), r.getType(), r.getStatus(), r.getPriority()); }
}
