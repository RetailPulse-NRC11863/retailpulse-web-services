package com.retailpulse.platform.subscription.application.internal.queryservices;

import com.retailpulse.platform.subscription.application.queryservices.SaasAccountQueryService;
import com.retailpulse.platform.subscription.domain.model.aggregates.SaasAccount;
import com.retailpulse.platform.subscription.domain.repositories.SaasAccountRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class SaasAccountQueryServiceImpl implements SaasAccountQueryService {
    private final SaasAccountRepository repository;
    public SaasAccountQueryServiceImpl(SaasAccountRepository repository) { this.repository = repository; }
    public Optional<SaasAccount> getCurrent() { return repository.findFirst(); }
    public Optional<SaasAccount> getCurrent(String ownerEmail) {
        if (ownerEmail != null && !ownerEmail.isBlank()) {
            return repository.findByOwnerEmail(ownerEmail).or(this::getCurrent);
        }
        return getCurrent();
    }
    public Optional<SaasAccount> getById(Long accountId) { return repository.findById(accountId); }
}
