package com.retailpulse.platform.storefoundation.application.internal.commandservices;

import com.retailpulse.platform.storefoundation.application.commandservices.StoreCommandService;
import com.retailpulse.platform.storefoundation.domain.model.aggregates.Store;
import com.retailpulse.platform.storefoundation.domain.model.commands.CreateStoreCommand;
import com.retailpulse.platform.storefoundation.domain.repositories.StoreRepository;
import org.springframework.stereotype.Service;

@Service
public class StoreCommandServiceImpl implements StoreCommandService {
    private final StoreRepository repository;
    public StoreCommandServiceImpl(StoreRepository repository) { this.repository = repository; }
    public Store handle(CreateStoreCommand command) { return repository.save(new Store(null, command.name(), command.address())); }
}
