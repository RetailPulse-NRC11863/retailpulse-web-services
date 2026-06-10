package com.retailpulse.platform.promotionoptimization.application.queryservices;

import com.retailpulse.platform.promotionoptimization.domain.model.aggregates.PromotionRecommendation;
import java.util.List;

public interface PromotionRecommendationQueryService {
    List<PromotionRecommendation> getAll();
    List<PromotionRecommendation> getActive();
    List<PromotionRecommendation> getProductOpportunities();
}
