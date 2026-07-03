package com.retailpulse.platform.storeoperations.interfaces.rest.transform;

import com.retailpulse.platform.storeoperations.domain.model.commands.CreateOperationalAlertCommand;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.CreateOperationalAlertResource;

public class CreateOperationalAlertCommandFromResourceAssembler {
    public static CreateOperationalAlertCommand toCommand(CreateOperationalAlertResource r) { return new CreateOperationalAlertCommand(r.storeId(), r.title(), r.description(), r.type(), r.priority(), r.productId(), r.zoneId(), r.source(), r.triggerReason()); }
}
