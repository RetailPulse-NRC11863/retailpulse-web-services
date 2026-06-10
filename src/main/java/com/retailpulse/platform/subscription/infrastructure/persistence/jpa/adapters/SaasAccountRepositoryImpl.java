package com.retailpulse.platform.subscription.infrastructure.persistence.jpa.adapters;

import com.retailpulse.platform.subscription.domain.model.aggregates.SaasAccount;
import com.retailpulse.platform.subscription.domain.repositories.SaasAccountRepository;
import com.retailpulse.platform.subscription.infrastructure.persistence.jpa.assemblers.SaasAccountPersistenceAssembler;
import com.retailpulse.platform.subscription.infrastructure.persistence.jpa.repositories.SaasAccountPersistenceRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class SaasAccountRepositoryImpl implements SaasAccountRepository {
    private final SaasAccountPersistenceRepository repository;
    private final SaasAccountPersistenceAssembler assembler = new SaasAccountPersistenceAssembler();
    public SaasAccountRepositoryImpl(SaasAccountPersistenceRepository repository) { this.repository = repository; }
    public SaasAccount save(SaasAccount account) { return assembler.toDomain(repository.save(assembler.toEntity(account))); }
    public Optional<SaasAccount> findById(Long accountId) { return repository.findById(accountId).map(assembler::toDomain); }
    public Optional<SaasAccount> findFirst() { return repository.findAll(PageRequest.of(0, 1)).stream().findFirst().map(assembler::toDomain); }
    public long count() { return repository.count(); }
}
