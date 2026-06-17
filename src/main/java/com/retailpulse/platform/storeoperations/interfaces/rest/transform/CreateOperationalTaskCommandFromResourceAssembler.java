package com.retailpulse.platform.storeoperations.interfaces.rest.transform;

import com.retailpulse.platform.storeoperations.domain.model.commands.CreateOperationalTaskCommand;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.CreateOperationalTaskResource;
import java.time.Instant;

public class CreateOperationalTaskCommandFromResourceAssembler {
    public static CreateOperationalTaskCommand toCommand(CreateOperationalTaskResource resource) {
        Instant createdAt = resource.createdAt() == null ? Instant.now() : resource.createdAt();
        return new CreateOperationalTaskCommand(resource.id(), resource.title(), resource.description(), resource.priority(), resource.status(), resource.zoneId(), resource.zoneName(), resource.alertId(), createdAt);
    }
}
