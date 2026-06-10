package com.retailpulse.platform.subscription.infrastructure.persistence.jpa.assemblers;

import com.retailpulse.platform.subscription.domain.model.aggregates.SaasAccount;
import com.retailpulse.platform.subscription.infrastructure.persistence.jpa.entities.SaasAccountPersistenceEntity;

public class SaasAccountPersistenceAssembler {
    public SaasAccount toDomain(SaasAccountPersistenceEntity e) { return new SaasAccount(e.getId(), e.getStoreId(), e.getOwnerEmail(), e.getPlanId(), e.getStatus()); }
    public SaasAccountPersistenceEntity toEntity(SaasAccount a) { SaasAccountPersistenceEntity e = new SaasAccountPersistenceEntity(); e.setId(a.getId()); e.setStoreId(a.getStoreId()); e.setOwnerEmail(a.getOwnerEmail()); e.setPlanId(a.getPlanId()); e.setStatus(a.getStatus()); return e; }
}
