package com.retailpulse.platform.promotionoptimization.infrastructure.persistence.jpa.repositories;

import com.retailpulse.platform.promotionoptimization.infrastructure.persistence.jpa.entities.PromotionRecommendationPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.RecommendationStatus;

public interface PromotionRecommendationPersistenceRepository extends JpaRepository<PromotionRecommendationPersistenceEntity, Long> {

    List<PromotionRecommendationPersistenceEntity> findByStatus(RecommendationStatus status);

}
