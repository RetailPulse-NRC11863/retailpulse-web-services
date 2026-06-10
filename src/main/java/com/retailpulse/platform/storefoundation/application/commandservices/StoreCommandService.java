package com.retailpulse.platform.storefoundation.application.commandservices;

import com.retailpulse.platform.storefoundation.domain.model.aggregates.Store;
import com.retailpulse.platform.storefoundation.domain.model.commands.CreateStoreCommand;

public interface StoreCommandService {
    Store handle(CreateStoreCommand command);
}
