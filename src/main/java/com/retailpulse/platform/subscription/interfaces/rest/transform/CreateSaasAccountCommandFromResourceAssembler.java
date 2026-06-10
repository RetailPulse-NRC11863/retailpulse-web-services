package com.retailpulse.platform.subscription.interfaces.rest.transform;

import com.retailpulse.platform.subscription.domain.model.commands.CreateSaasAccountCommand;
import com.retailpulse.platform.subscription.interfaces.rest.resources.CreateSaasAccountResource;

public class CreateSaasAccountCommandFromResourceAssembler {
    public static CreateSaasAccountCommand toCommand(CreateSaasAccountResource r) { return new CreateSaasAccountCommand(r.storeId(), r.ownerEmail(), r.planId()); }
}
