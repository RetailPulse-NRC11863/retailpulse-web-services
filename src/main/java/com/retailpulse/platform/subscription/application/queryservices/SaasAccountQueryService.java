package com.retailpulse.platform.subscription.application.queryservices;

import com.retailpulse.platform.subscription.domain.model.aggregates.SaasAccount;
import java.util.Optional;

public interface SaasAccountQueryService {
    Optional<SaasAccount> getCurrent();
    Optional<SaasAccount> getCurrent(String ownerEmail);
    Optional<SaasAccount> getById(Long accountId);
}
