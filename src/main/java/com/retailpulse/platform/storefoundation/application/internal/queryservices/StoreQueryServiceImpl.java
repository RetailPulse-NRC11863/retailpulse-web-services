package com.retailpulse.platform.storefoundation.application.internal.queryservices;

import com.retailpulse.platform.storefoundation.application.queryservices.StoreQueryService;
import com.retailpulse.platform.storefoundation.domain.model.aggregates.Store;
import com.retailpulse.platform.storefoundation.domain.repositories.StoreRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StoreQueryServiceImpl implements StoreQueryService {
    private final StoreRepository repository;
    public StoreQueryServiceImpl(StoreRepository repository) { this.repository = repository; }
    public List<Store> handleGetAll() { return repository.findAll(); }
    public Optional<Store> handleGetById(Long storeId) { return repository.findById(storeId); }
}
