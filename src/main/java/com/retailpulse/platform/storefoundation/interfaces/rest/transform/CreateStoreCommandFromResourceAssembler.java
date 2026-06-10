package com.retailpulse.platform.storefoundation.interfaces.rest.transform;

import com.retailpulse.platform.storefoundation.domain.model.commands.CreateStoreCommand;
import com.retailpulse.platform.storefoundation.interfaces.rest.resources.CreateStoreResource;

public class CreateStoreCommandFromResourceAssembler {
    public static CreateStoreCommand toCommand(CreateStoreResource resource) { return new CreateStoreCommand(resource.name(), resource.address()); }
}
