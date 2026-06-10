package com.retailpulse.platform.subscription.infrastructure.persistence.jpa.adapters;

import com.retailpulse.platform.subscription.domain.model.aggregates.SubscriptionPlan;
import com.retailpulse.platform.subscription.domain.repositories.SubscriptionPlanRepository;
import com.retailpulse.platform.subscription.infrastructure.persistence.jpa.assemblers.SubscriptionPlanPersistenceAssembler;
import com.retailpulse.platform.subscription.infrastructure.persistence.jpa.repositories.SubscriptionPlanPersistenceRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class SubscriptionPlanRepositoryImpl implements SubscriptionPlanRepository {
    private final SubscriptionPlanPersistenceRepository repository;
    private final SubscriptionPlanPersistenceAssembler assembler = new SubscriptionPlanPersistenceAssembler();
    public SubscriptionPlanRepositoryImpl(SubscriptionPlanPersistenceRepository repository) { this.repository = repository; }
    public SubscriptionPlan save(SubscriptionPlan plan) { return assembler.toDomain(repository.save(assembler.toEntity(plan))); }
    public List<SubscriptionPlan> findAll() { return repository.findAll().stream().map(assembler::toDomain).toList(); }
    public Optional<SubscriptionPlan> findById(Long planId) { return repository.findById(planId).map(assembler::toDomain); }
    public long count() { return repository.count(); }
}
