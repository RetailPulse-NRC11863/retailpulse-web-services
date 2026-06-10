package com.retailpulse.platform.trafficanalytics.interfaces.rest.transform;

import com.retailpulse.platform.trafficanalytics.domain.model.commands.RegisterMovementEventCommand;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.RegisterMovementEventResource;

public class RegisterMovementEventCommandFromResourceAssembler {
    public static RegisterMovementEventCommand toCommand(RegisterMovementEventResource resource) { return new RegisterMovementEventCommand(resource.zoneId(), resource.eventType(), resource.occurredAt()); }
}
