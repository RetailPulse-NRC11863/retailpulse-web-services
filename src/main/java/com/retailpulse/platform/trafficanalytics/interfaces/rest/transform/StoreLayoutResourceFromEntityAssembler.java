package com.retailpulse.platform.trafficanalytics.interfaces.rest.transform;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.LayoutZone;
import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.StoreLayout;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.StoreLayoutResource;
import java.util.List;

public class StoreLayoutResourceFromEntityAssembler {
    public static StoreLayoutResource toResource(StoreLayout storeLayout, List<LayoutZone> layoutZones) {
        return new StoreLayoutResource(
            storeLayout.getId(),
            storeLayout.getName(),
            storeLayout.getStoreName(),
            storeLayout.getWidth(),
            storeLayout.getHeight(),
            storeLayout.getActive(),
            layoutZones.stream().map(LayoutZoneResourceFromEntityAssembler::toResource).toList()
        );
    }
}
