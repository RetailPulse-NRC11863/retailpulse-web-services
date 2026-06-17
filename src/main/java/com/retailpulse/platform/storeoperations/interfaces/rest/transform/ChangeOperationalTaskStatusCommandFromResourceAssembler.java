package com.retailpulse.platform.storeoperations.interfaces.rest.transform;

import com.retailpulse.platform.storeoperations.domain.model.commands.ChangeOperationalTaskStatusCommand;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.ChangeOperationalTaskStatusResource;

public class ChangeOperationalTaskStatusCommandFromResourceAssembler {
    public static ChangeOperationalTaskStatusCommand toCommand(String id, ChangeOperationalTaskStatusResource resource) {
        return new ChangeOperationalTaskStatusCommand(id, resource.status());
    }
}
