package com.retailpulse.platform.trafficanalytics.domain.model.commands;

import com.retailpulse.platform.trafficanalytics.domain.model.valueobjects.ZoneType;

public record CreateLayoutZoneCommand(String id, String name, Integer x, Integer y, Integer width, Integer height, ZoneType type) {
}
