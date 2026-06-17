package com.retailpulse.platform.storeoperations.interfaces.rest.transform;

import com.retailpulse.platform.storeoperations.domain.model.commands.UpdateOperationalAlertCommand;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.UpdateOperationalAlertResource;

public class UpdateOperationalAlertCommandFromResourceAssembler {
    public static UpdateOperationalAlertCommand toCommand(String id, UpdateOperationalAlertResource resource) {
        return new UpdateOperationalAlertCommand(id, resource.type(), resource.priority(), resource.status(), resource.message(), resource.zoneId(), resource.zoneName(), resource.productId(), resource.productName());
    }
}
