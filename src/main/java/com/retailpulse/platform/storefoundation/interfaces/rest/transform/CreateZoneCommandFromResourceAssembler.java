package com.retailpulse.platform.storefoundation.interfaces.rest.transform;

import com.retailpulse.platform.storefoundation.domain.model.commands.CreateZoneCommand;
import com.retailpulse.platform.storefoundation.interfaces.rest.resources.CreateZoneResource;

public class CreateZoneCommandFromResourceAssembler {
    public static CreateZoneCommand toCommand(CreateZoneResource resource) { return new CreateZoneCommand(resource.storeId(), resource.name(), resource.type(), resource.capacity(), resource.x(), resource.y(), resource.width(), resource.height()); }
}
