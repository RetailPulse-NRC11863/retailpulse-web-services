package com.retailpulse.platform.storeoperations.interfaces.rest.transform;

import com.retailpulse.platform.storeoperations.domain.model.commands.CreateOperationalTaskCommand;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.CreateOperationalTaskResource;

public class CreateOperationalTaskCommandFromResourceAssembler {
    public static CreateOperationalTaskCommand toCommand(CreateOperationalTaskResource r) { return new CreateOperationalTaskCommand(r.storeId(), r.title(), r.description(), r.priority(), r.alertId(), r.productId(), r.zoneId(), r.source(), r.triggerReason()); }
}
