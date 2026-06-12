package com.retailpulse.platform.trafficanalytics.interfaces.rest.transform;

import com.retailpulse.platform.trafficanalytics.domain.model.commands.UpdateLayoutZoneCommand;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.UpdateLayoutZoneResource;

public class UpdateLayoutZoneCommandFromResourceAssembler {
    public static UpdateLayoutZoneCommand toCommand(String zoneId, UpdateLayoutZoneResource resource) {
        return new UpdateLayoutZoneCommand(zoneId, resource.name(), resource.x(), resource.y(), resource.width(), resource.height(), resource.type());
    }
}
