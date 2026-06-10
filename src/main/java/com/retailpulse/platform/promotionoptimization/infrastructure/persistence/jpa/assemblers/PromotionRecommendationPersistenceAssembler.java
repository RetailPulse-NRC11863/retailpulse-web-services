package com.retailpulse.platform.promotionoptimization.infrastructure.persistence.jpa.assemblers;

import com.retailpulse.platform.promotionoptimization.domain.model.aggregates.PromotionRecommendation;
import com.retailpulse.platform.promotionoptimization.infrastructure.persistence.jpa.entities.PromotionRecommendationPersistenceEntity;

public class PromotionRecommendationPersistenceAssembler {
    public PromotionRecommendation toDomain(PromotionRecommendationPersistenceEntity e) { return new PromotionRecommendation(e.getId(), e.getProductId(), e.getTitle(), e.getDescription(), e.getType(), e.getStatus(), e.getPriority()); }
    public PromotionRecommendationPersistenceEntity toEntity(PromotionRecommendation r) { PromotionRecommendationPersistenceEntity e = new PromotionRecommendationPersistenceEntity(); e.setId(r.getId()); e.setProductId(r.getProductId()); e.setTitle(r.getTitle()); e.setDescription(r.getDescription()); e.setType(r.getType()); e.setStatus(r.getStatus()); e.setPriority(r.getPriority()); return e; }
}
