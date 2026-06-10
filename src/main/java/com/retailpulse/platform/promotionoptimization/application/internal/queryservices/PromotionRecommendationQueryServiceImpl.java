package com.retailpulse.platform.promotionoptimization.application.internal.queryservices;

import com.retailpulse.platform.promotionoptimization.application.queryservices.PromotionRecommendationQueryService;
import com.retailpulse.platform.promotionoptimization.domain.model.aggregates.PromotionRecommendation;
import com.retailpulse.platform.promotionoptimization.domain.repositories.PromotionRecommendationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PromotionRecommendationQueryServiceImpl implements PromotionRecommendationQueryService {
    private final PromotionRecommendationRepository repository;
    public PromotionRecommendationQueryServiceImpl(PromotionRecommendationRepository repository) { this.repository = repository; }
    public List<PromotionRecommendation> getAll() { return repository.findAll(); }
    public List<PromotionRecommendation> getActive() { return repository.findActive(); }
    public List<PromotionRecommendation> getProductOpportunities() { return repository.findActive(); }
}
