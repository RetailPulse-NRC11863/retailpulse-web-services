package com.retailpulse.platform.storefoundation.domain.repositories;

import com.retailpulse.platform.storefoundation.domain.model.aggregates.Store;
import java.util.List;
import java.util.Optional;

public interface StoreRepository {
    Store save(Store store);
    List<Store> findAll();
    Optional<Store> findById(Long storeId);
    long count();
}
