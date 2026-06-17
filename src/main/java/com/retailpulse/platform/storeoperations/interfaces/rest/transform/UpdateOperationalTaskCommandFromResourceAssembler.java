package com.retailpulse.platform.storeoperations.interfaces.rest.transform;

import com.retailpulse.platform.storeoperations.domain.model.commands.UpdateOperationalTaskCommand;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.UpdateOperationalTaskResource;

public class UpdateOperationalTaskCommandFromResourceAssembler {
    public static UpdateOperationalTaskCommand toCommand(String id, UpdateOperationalTaskResource resource) {
        return new UpdateOperationalTaskCommand(id, resource.title(), resource.description(), resource.priority(), resource.status(), resource.zoneId(), resource.zoneName(), resource.alertId());
    }
}
