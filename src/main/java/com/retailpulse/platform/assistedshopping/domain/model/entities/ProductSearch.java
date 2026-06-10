package com.retailpulse.platform.assistedshopping.domain.model.entities;

import com.retailpulse.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.retailpulse.platform.assistedshopping.domain.model.valueobjects.SearchResultStatus;

public class ProductSearch extends AuditableAbstractAggregateRoot {
    private Long sessionId;
    private String query;
    private Long productId;
    private SearchResultStatus status;

    public ProductSearch() {
    }

    public ProductSearch(Long id, Long sessionId, String query, Long productId, SearchResultStatus status) {
        setId(id);
        this.sessionId = sessionId;
        this.query = query;
        this.productId = productId;
        this.status = status;
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
