package com.retailpulse.platform.subscription.application.commandservices;

import com.retailpulse.platform.subscription.domain.model.aggregates.SaasAccount;
import com.retailpulse.platform.subscription.domain.model.commands.CreateSaasAccountCommand;
import com.retailpulse.platform.subscription.domain.model.commands.ChangeSubscriptionPlanCommand;

public interface SaasAccountCommandService {
    SaasAccount handle(CreateSaasAccountCommand command);
    SaasAccount handle(ChangeSubscriptionPlanCommand command);
}
