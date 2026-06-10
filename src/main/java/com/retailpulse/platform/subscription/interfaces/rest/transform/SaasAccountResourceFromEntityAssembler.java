package com.retailpulse.platform.subscription.interfaces.rest.transform;

import com.retailpulse.platform.subscription.domain.model.aggregates.SaasAccount;
import com.retailpulse.platform.subscription.interfaces.rest.resources.SaasAccountResource;

public class SaasAccountResourceFromEntityAssembler {
    public static SaasAccountResource toResource(SaasAccount a) { return new SaasAccountResource(a.getId(), a.getStoreId(), a.getOwnerEmail(), a.getPlanId(), a.getStatus()); }
}
