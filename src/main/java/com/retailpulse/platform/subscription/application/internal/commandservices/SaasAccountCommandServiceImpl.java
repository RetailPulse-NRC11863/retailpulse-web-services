package com.retailpulse.platform.subscription.application.internal.commandservices;

import com.retailpulse.platform.subscription.application.commandservices.SaasAccountCommandService;
import com.retailpulse.platform.subscription.domain.model.aggregates.SaasAccount;
import com.retailpulse.platform.subscription.domain.model.commands.ChangeSubscriptionPlanCommand;
import com.retailpulse.platform.subscription.domain.model.commands.CreateSaasAccountCommand;
import com.retailpulse.platform.subscription.domain.model.valueobjects.SubscriptionStatus;
import com.retailpulse.platform.subscription.domain.repositories.SaasAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class SaasAccountCommandServiceImpl implements SaasAccountCommandService {
    private final SaasAccountRepository repository;
    public SaasAccountCommandServiceImpl(SaasAccountRepository repository) { this.repository = repository; }
    public SaasAccount handle(CreateSaasAccountCommand c) { return repository.save(new SaasAccount(null, c.storeId(), c.ownerEmail(), c.planId(), SubscriptionStatus.ACTIVE)); }
    public SaasAccount handle(ChangeSubscriptionPlanCommand c) { SaasAccount a = repository.findById(c.accountId()).orElseThrow(() -> new IllegalArgumentException("SaaS account not found")); a.changePlan(c.planId()); return repository.save(a); }
}
