package com.retailpulse.platform.assistedshopping.infrastructure.persistence.jpa.entities;

import com.retailpulse.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import com.retailpulse.platform.assistedshopping.domain.model.valueobjects.SearchResultStatus;

@Entity
@Table(name = "product_searches")
public class ProductSearchPersistenceEntity extends AuditableAbstractPersistenceEntity {
    private Long sessionId;

    private String query;

    private Long productId;

    @Enumerated(EnumType.STRING)
    private SearchResultStatus status;

    public ProductSearchPersistenceEntity() {
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public SearchResultStatus getStatus() {
        return status;
    }

    public void setStatus(SearchResultStatus status) {
        this.status = status;
    }


}
