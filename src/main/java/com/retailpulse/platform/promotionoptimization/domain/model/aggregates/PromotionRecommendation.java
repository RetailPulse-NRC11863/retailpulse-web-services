package com.retailpulse.platform.promotionoptimization.domain.model.aggregates;

import com.retailpulse.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.RecommendationType;
import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.RecommendationStatus;
import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.CommercialPriority;

public class PromotionRecommendation extends AuditableAbstractAggregateRoot {
    private Long productId;
    private String title;
    private String description;
    private RecommendationType type;
    private RecommendationStatus status;
    private CommercialPriority priority;

    public PromotionRecommendation() {
    }

    public PromotionRecommendation(Long id, Long productId, String title, String description, RecommendationType type, RecommendationStatus status, CommercialPriority priority) {
        setId(id);
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.status = status;
        this.priority = priority;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RecommendationType getType() {
        return type;
    }

    public void setType(RecommendationType type) {
        this.type = type;
    }

    public RecommendationStatus getStatus() {
        return status;
    }

    public void setStatus(RecommendationStatus status) {
        this.status = status;
    }

    public CommercialPriority getPriority() {
        return priority;
    }

    public void setPriority(CommercialPriority priority) {
        this.priority = priority;
    }


    public void apply() { this.status = RecommendationStatus.APPLIED; }

}
