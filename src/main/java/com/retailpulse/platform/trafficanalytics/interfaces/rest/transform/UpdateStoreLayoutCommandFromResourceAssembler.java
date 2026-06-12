package com.retailpulse.platform.trafficanalytics.interfaces.rest.transform;

import com.retailpulse.platform.trafficanalytics.domain.model.commands.UpdateStoreLayoutCommand;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.UpdateStoreLayoutResource;

public class UpdateStoreLayoutCommandFromResourceAssembler {
    public static UpdateStoreLayoutCommand toCommand(String layoutId, UpdateStoreLayoutResource resource) {
        return new UpdateStoreLayoutCommand(layoutId, resource.name(), resource.storeName(), resource.width(), resource.height(), resource.active());
    }
}
