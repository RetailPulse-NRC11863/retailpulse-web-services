package com.retailpulse.platform.assistedshopping.domain.repositories;

import com.retailpulse.platform.assistedshopping.domain.model.aggregates.KioskSession;
import java.util.Optional;

public interface KioskSessionRepository {
    KioskSession save(KioskSession session);
    Optional<KioskSession> findById(Long sessionId);
    long count();
}
