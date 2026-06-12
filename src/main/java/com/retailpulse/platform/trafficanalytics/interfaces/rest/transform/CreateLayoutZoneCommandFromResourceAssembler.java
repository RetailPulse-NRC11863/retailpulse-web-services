package com.retailpulse.platform.trafficanalytics.interfaces.rest.transform;

import com.retailpulse.platform.trafficanalytics.domain.model.commands.CreateLayoutZoneCommand;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.CreateLayoutZoneResource;

public class CreateLayoutZoneCommandFromResourceAssembler {
    public static CreateLayoutZoneCommand toCommand(CreateLayoutZoneResource resource) {
        return new CreateLayoutZoneCommand(resource.id(), resource.name(), resource.x(), resource.y(), resource.width(), resource.height(), resource.type());
    }
}
