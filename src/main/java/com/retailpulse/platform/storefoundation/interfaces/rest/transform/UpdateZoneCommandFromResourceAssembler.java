package com.retailpulse.platform.storefoundation.interfaces.rest.transform;

import com.retailpulse.platform.storefoundation.domain.model.commands.UpdateZoneCommand;
import com.retailpulse.platform.storefoundation.interfaces.rest.resources.UpdateZoneResource;

public class UpdateZoneCommandFromResourceAssembler {
    public static UpdateZoneCommand toCommand(Long zoneId, UpdateZoneResource resource) { return new UpdateZoneCommand(zoneId, resource.name(), resource.type(), resource.capacity(), resource.x(), resource.y(), resource.width(), resource.height()); }
}
