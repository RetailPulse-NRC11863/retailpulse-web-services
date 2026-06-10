package com.retailpulse.platform.promotionoptimization.infrastructure.persistence.jpa.entities;

import com.retailpulse.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.RecommendationType;
import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.RecommendationStatus;
import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.CommercialPriority;

@Entity
@Table(name = "promotion_recommendations")
public class PromotionRecommendationPersistenceEntity extends AuditableAbstractPersistenceEntity {
    private Long productId;

    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private RecommendationType type;

    @Enumerated(EnumType.STRING)
    private RecommendationStatus status;

    @Enumerated(EnumType.STRING)
    private CommercialPriority priority;

    public PromotionRecommendationPersistenceEntity() {
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


}
