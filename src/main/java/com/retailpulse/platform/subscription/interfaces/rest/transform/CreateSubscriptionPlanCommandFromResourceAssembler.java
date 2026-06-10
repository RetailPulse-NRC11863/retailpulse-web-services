package com.retailpulse.platform.subscription.interfaces.rest.transform;

import com.retailpulse.platform.subscription.domain.model.commands.CreateSubscriptionPlanCommand;
import com.retailpulse.platform.subscription.interfaces.rest.resources.CreateSubscriptionPlanResource;

public class CreateSubscriptionPlanCommandFromResourceAssembler {
    public static CreateSubscriptionPlanCommand toCommand(CreateSubscriptionPlanResource r) { return new CreateSubscriptionPlanCommand(r.name(), r.price(), r.billingPeriod(), r.maxStores(), r.description()); }
}
