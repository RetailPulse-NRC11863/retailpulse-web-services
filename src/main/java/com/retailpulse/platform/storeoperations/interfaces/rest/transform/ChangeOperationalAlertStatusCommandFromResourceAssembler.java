package com.retailpulse.platform.storeoperations.interfaces.rest.transform;

import com.retailpulse.platform.storeoperations.domain.model.commands.ChangeOperationalAlertStatusCommand;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.ChangeOperationalAlertStatusResource;

public class ChangeOperationalAlertStatusCommandFromResourceAssembler {
    public static ChangeOperationalAlertStatusCommand toCommand(String id, ChangeOperationalAlertStatusResource resource) {
        return new ChangeOperationalAlertStatusCommand(id, resource.status());
    }
}
