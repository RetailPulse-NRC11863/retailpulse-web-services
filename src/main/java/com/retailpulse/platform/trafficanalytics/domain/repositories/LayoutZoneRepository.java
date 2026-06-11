package com.retailpulse.platform.trafficanalytics.domain.repositories;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.LayoutZone;
import java.util.List;
import java.util.Optional;

public interface LayoutZoneRepository {
    List<LayoutZone> findAll();
    Optional<LayoutZone> findById(String id);
    LayoutZone save(LayoutZone layoutZone);
    boolean existsById(String id);
}
