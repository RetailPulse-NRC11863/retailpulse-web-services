package com.retailpulse.platform.promotionoptimization.infrastructure.persistence.jpa.adapters;

import com.retailpulse.platform.promotionoptimization.domain.model.aggregates.PromotionRecommendation;
import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.RecommendationStatus;
import com.retailpulse.platform.promotionoptimization.domain.repositories.PromotionRecommendationRepository;
import com.retailpulse.platform.promotionoptimization.infrastructure.persistence.jpa.assemblers.PromotionRecommendationPersistenceAssembler;
import com.retailpulse.platform.promotionoptimization.infrastructure.persistence.jpa.repositories.PromotionRecommendationPersistenceRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class PromotionRecommendationRepositoryImpl implements PromotionRecommendationRepository {
    private final PromotionRecommendationPersistenceRepository repository;
    private final PromotionRecommendationPersistenceAssembler assembler = new PromotionRecommendationPersistenceAssembler();
    public PromotionRecommendationRepositoryImpl(PromotionRecommendationPersistenceRepository repository) { this.repository = repository; }
    public PromotionRecommendation save(PromotionRecommendation recommendation) { return assembler.toDomain(repository.save(assembler.toEntity(recommendation))); }
    public List<PromotionRecommendation> findAll() { return repository.findAll().stream().map(assembler::toDomain).toList(); }
    public List<PromotionRecommendation> findActive() { return repository.findByStatus(RecommendationStatus.ACTIVE).stream().map(assembler::toDomain).toList(); }
    public Optional<PromotionRecommendation> findById(Long recommendationId) { return repository.findById(recommendationId).map(assembler::toDomain); }
    public long count() { return repository.count(); }
}
