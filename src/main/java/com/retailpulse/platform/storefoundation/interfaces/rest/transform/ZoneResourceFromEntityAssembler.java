package com.retailpulse.platform.storefoundation.interfaces.rest.transform;

import com.retailpulse.platform.storefoundation.domain.model.entities.Zone;
import com.retailpulse.platform.storefoundation.interfaces.rest.resources.ZoneResource;

public class ZoneResourceFromEntityAssembler {
    public static ZoneResource toResource(Zone zone) { return new ZoneResource(zone.getId(), zone.getStoreId(), zone.getName(), zone.getType(), zone.getCapacity(), zone.getX(), zone.getY(), zone.getWidth(), zone.getHeight()); }
}
