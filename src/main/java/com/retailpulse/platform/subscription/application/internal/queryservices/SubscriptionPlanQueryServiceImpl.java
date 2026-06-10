package com.retailpulse.platform.subscription.application.internal.queryservices;

import com.retailpulse.platform.subscription.application.queryservices.SubscriptionPlanQueryService;
import com.retailpulse.platform.subscription.domain.model.aggregates.SubscriptionPlan;
import com.retailpulse.platform.subscription.domain.repositories.SubscriptionPlanRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionPlanQueryServiceImpl implements SubscriptionPlanQueryService {
    private final SubscriptionPlanRepository repository;
    public SubscriptionPlanQueryServiceImpl(SubscriptionPlanRepository repository) { this.repository = repository; }
    public List<SubscriptionPlan> getAll() { return repository.findAll(); }
    public Optional<SubscriptionPlan> getById(Long planId) { return repository.findById(planId); }
}
