package com.retailpulse.platform.storefoundation.application.queryservices;

import com.retailpulse.platform.storefoundation.domain.model.entities.Zone;
import java.util.List;
import java.util.Optional;

public interface ZoneQueryService {
    List<Zone> handleGetAll();
    Optional<Zone> handleGetById(Long zoneId);
}
