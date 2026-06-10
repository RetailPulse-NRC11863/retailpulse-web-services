package com.retailpulse.platform.promotionoptimization.interfaces.rest.resources;

import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.CommercialPriority;
import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.RecommendationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePromotionRecommendationResource(@NotNull Long productId, @NotBlank String title, String description, @NotNull RecommendationType type, @NotNull CommercialPriority priority) {
}
