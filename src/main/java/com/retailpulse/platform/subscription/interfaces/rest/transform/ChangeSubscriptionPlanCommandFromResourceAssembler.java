package com.retailpulse.platform.subscription.interfaces.rest.transform;

import com.retailpulse.platform.subscription.domain.model.commands.ChangeSubscriptionPlanCommand;
import com.retailpulse.platform.subscription.interfaces.rest.resources.ChangeSubscriptionPlanResource;

public class ChangeSubscriptionPlanCommandFromResourceAssembler {
    public static ChangeSubscriptionPlanCommand toCommand(Long accountId, ChangeSubscriptionPlanResource r) { return new ChangeSubscriptionPlanCommand(accountId, r.planId()); }
}
