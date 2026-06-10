package com.retailpulse.platform.promotionoptimization.application.internal.commandservices;

import com.retailpulse.platform.promotionoptimization.application.commandservices.PromotionRecommendationCommandService;
import com.retailpulse.platform.promotionoptimization.domain.model.aggregates.PromotionRecommendation;
import com.retailpulse.platform.promotionoptimization.domain.model.commands.ApplyPromotionRecommendationCommand;
import com.retailpulse.platform.promotionoptimization.domain.model.commands.CreatePromotionRecommendationCommand;
import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.RecommendationStatus;
import com.retailpulse.platform.promotionoptimization.domain.repositories.PromotionRecommendationRepository;
import org.springframework.stereotype.Service;

@Service
public class PromotionRecommendationCommandServiceImpl implements PromotionRecommendationCommandService {
    private final PromotionRecommendationRepository repository;
    public PromotionRecommendationCommandServiceImpl(PromotionRecommendationRepository repository) { this.repository = repository; }
    public PromotionRecommendation handle(CreatePromotionRecommendationCommand c) { return repository.save(new PromotionRecommendation(null, c.productId(), c.title(), c.description(), c.type(), RecommendationStatus.ACTIVE, c.priority())); }
    public PromotionRecommendation handle(ApplyPromotionRecommendationCommand c) { PromotionRecommendation r = repository.findById(c.recommendationId()).orElseThrow(() -> new IllegalArgumentException("Recommendation not found")); r.apply(); return repository.save(r); }
}
