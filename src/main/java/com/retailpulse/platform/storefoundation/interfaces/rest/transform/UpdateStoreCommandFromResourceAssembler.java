package com.retailpulse.platform.storefoundation.interfaces.rest.transform;

import com.retailpulse.platform.storefoundation.domain.model.commands.UpdateStoreCommand;
import com.retailpulse.platform.storefoundation.interfaces.rest.resources.UpdateStoreResource;

public class UpdateStoreCommandFromResourceAssembler {
    public static UpdateStoreCommand toCommand(Long storeId, UpdateStoreResource resource) {
        return new UpdateStoreCommand(storeId, resource.name(), resource.address(), resource.managerName(), resource.status());
    }
}
