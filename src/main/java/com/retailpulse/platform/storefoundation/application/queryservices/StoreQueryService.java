package com.retailpulse.platform.storefoundation.application.queryservices;

import com.retailpulse.platform.storefoundation.domain.model.aggregates.Store;
import java.util.List;
import java.util.Optional;

public interface StoreQueryService {
    List<Store> handleGetAll();
    Optional<Store> handleGetById(Long storeId);
}
