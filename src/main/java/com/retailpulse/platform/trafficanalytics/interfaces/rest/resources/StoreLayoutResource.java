package com.retailpulse.platform.trafficanalytics.interfaces.rest.resources;

import java.util.List;

public record StoreLayoutResource(String id, String name, String storeName, Integer width, Integer height, Boolean active, List<LayoutZoneResource> zones) {
}
