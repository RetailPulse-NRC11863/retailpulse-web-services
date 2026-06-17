package com.retailpulse.platform.storeoperations.interfaces.rest.transform;

import com.retailpulse.platform.storeoperations.domain.model.commands.CreateOperationalAlertCommand;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.CreateOperationalAlertResource;

public class CreateOperationalAlertCommandFromResourceAssembler {
    public static CreateOperationalAlertCommand toCommand(CreateOperationalAlertResource resource) {
        return new CreateOperationalAlertCommand(resource.id(), resource.type(), resource.priority(), resource.status(), resource.message(), resource.zoneId(), resource.zoneName(), resource.productId(), resource.productName(), resource.createdAt());
    }
}
