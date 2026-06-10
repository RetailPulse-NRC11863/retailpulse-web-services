package com.retailpulse.platform.storefoundation.domain.repositories;

import com.retailpulse.platform.storefoundation.domain.model.entities.Zone;
import java.util.List;
import java.util.Optional;

public interface ZoneRepository {
    Zone save(Zone zone);
    List<Zone> findAll();
    Optional<Zone> findById(Long zoneId);
    void deleteById(Long zoneId);
    long count();
}
