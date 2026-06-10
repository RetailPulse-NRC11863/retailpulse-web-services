package com.retailpulse.platform.promotionoptimization.domain.repositories;

import com.retailpulse.platform.promotionoptimization.domain.model.aggregates.PromotionRecommendation;
import java.util.List;
import java.util.Optional;

public interface PromotionRecommendationRepository {
    PromotionRecommendation save(PromotionRecommendation recommendation);
    List<PromotionRecommendation> findAll();
    List<PromotionRecommendation> findActive();
    Optional<PromotionRecommendation> findById(Long recommendationId);
    long count();
}
