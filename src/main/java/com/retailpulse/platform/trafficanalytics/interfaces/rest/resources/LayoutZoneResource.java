package com.retailpulse.platform.trafficanalytics.interfaces.rest.resources;

import com.retailpulse.platform.trafficanalytics.domain.model.valueobjects.ZoneType;

public record LayoutZoneResource(String id, String name, Integer x, Integer y, Integer width, Integer height, ZoneType type) {
}
