package com.retailpulse.platform.subscription.application.commandservices;

import com.retailpulse.platform.subscription.domain.model.aggregates.SubscriptionPlan;
import com.retailpulse.platform.subscription.domain.model.commands.CreateSubscriptionPlanCommand;

public interface SubscriptionPlanCommandService {
    SubscriptionPlan handle(CreateSubscriptionPlanCommand command);
}
