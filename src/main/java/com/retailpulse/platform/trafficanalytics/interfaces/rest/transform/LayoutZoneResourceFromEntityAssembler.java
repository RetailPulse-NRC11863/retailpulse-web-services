package com.retailpulse.platform.trafficanalytics.interfaces.rest.transform;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.LayoutZone;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.LayoutZoneResource;

public class LayoutZoneResourceFromEntityAssembler {
    public static LayoutZoneResource toResource(LayoutZone layoutZone) {
        return new LayoutZoneResource(layoutZone.getId(), layoutZone.getName(), layoutZone.getX(), layoutZone.getY(), layoutZone.getWidth(), layoutZone.getHeight(), layoutZone.getType());
    }
}
