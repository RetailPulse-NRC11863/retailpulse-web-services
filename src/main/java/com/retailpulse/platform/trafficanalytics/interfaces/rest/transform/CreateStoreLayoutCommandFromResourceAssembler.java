package com.retailpulse.platform.trafficanalytics.interfaces.rest.transform;

import com.retailpulse.platform.trafficanalytics.domain.model.commands.CreateStoreLayoutCommand;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.CreateStoreLayoutResource;

public class CreateStoreLayoutCommandFromResourceAssembler {
    public static CreateStoreLayoutCommand toCommand(CreateStoreLayoutResource resource) {
        return new CreateStoreLayoutCommand(resource.id(), resource.name(), resource.storeName(), resource.width(), resource.height(), resource.active());
    }
}
