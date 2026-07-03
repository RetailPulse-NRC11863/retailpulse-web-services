package com.retailpulse.platform.storefoundation.domain.model.commands;

import com.retailpulse.platform.storefoundation.domain.model.valueobjects.ZoneType;

public record UpdateZoneCommand(Long zoneId, String name, ZoneType type, Integer capacity, Integer x, Integer y, Integer width, Integer height) {
}
