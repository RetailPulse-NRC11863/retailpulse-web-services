package com.retailpulse.platform.storefoundation.domain.model.aggregates;

import com.retailpulse.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

public class Store extends AuditableAbstractAggregateRoot {
    private String name;
    private String address;

    public Store() {
    }

    public Store(Long id, String name, String address) {
        setId(id);
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
