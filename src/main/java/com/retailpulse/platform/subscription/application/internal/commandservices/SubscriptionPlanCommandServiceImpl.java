package com.retailpulse.platform.subscription.application.internal.commandservices;

import com.retailpulse.platform.subscription.application.commandservices.SubscriptionPlanCommandService;
import com.retailpulse.platform.subscription.domain.model.aggregates.SubscriptionPlan;
import com.retailpulse.platform.subscription.domain.model.commands.CreateSubscriptionPlanCommand;
import com.retailpulse.platform.subscription.domain.repositories.SubscriptionPlanRepository;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionPlanCommandServiceImpl implements SubscriptionPlanCommandService {
    private final SubscriptionPlanRepository repository;
    public SubscriptionPlanCommandServiceImpl(SubscriptionPlanRepository repository) { this.repository = repository; }
    public SubscriptionPlan handle(CreateSubscriptionPlanCommand c) { return repository.save(new SubscriptionPlan(null, c.name(), c.price(), c.billingPeriod(), c.maxStores(), c.description())); }
}
