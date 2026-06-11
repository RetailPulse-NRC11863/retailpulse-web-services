package com.retailpulse.platform.trafficanalytics.domain.repositories;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.StoreLayout;
import java.util.List;
import java.util.Optional;

public interface StoreLayoutRepository {
    List<StoreLayout> findAll();
    Optional<StoreLayout> findById(String id);
    Optional<StoreLayout> findCurrent();
    StoreLayout save(StoreLayout storeLayout);
    boolean existsById(String id);
}
