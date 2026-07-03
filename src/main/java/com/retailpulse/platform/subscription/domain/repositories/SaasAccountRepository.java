package com.retailpulse.platform.subscription.domain.repositories;

import com.retailpulse.platform.subscription.domain.model.aggregates.SaasAccount;
import java.util.List;
import java.util.Optional;

public interface SaasAccountRepository {
    SaasAccount save(SaasAccount account);
    Optional<SaasAccount> findById(Long accountId);
    Optional<SaasAccount> findByOwnerEmail(String ownerEmail);
    Optional<SaasAccount> findFirst();
    long count();
}
