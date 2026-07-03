package com.retailpulse.platform.storefoundation.domain.model.aggregates;

import com.retailpulse.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

public class Store extends AuditableAbstractAggregateRoot {
    private String name;
    private String address;
    private String managerName;
    private String status;

    public Store() {
    }

    public Store(Long id, String name, String address) {
        this(id, name, address, null, "ACTIVE");
    }

    public Store(Long id, String name, String address, String managerName, String status) {
        setId(id);
        this.name = name;
        this.address = address;
        this.managerName = managerName;
        this.status = status == null || status.isBlank() ? "ACTIVE" : status;
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

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void updateProfile(String name, String address, String managerName, String status) {
        this.name = name;
        this.address = address;
        this.managerName = managerName;
        this.status = status == null || status.isBlank() ? "ACTIVE" : status;
    }
}
