package com.retailpulse.platform.storefoundation.domain.model.commands;

import com.retailpulse.platform.storefoundation.domain.model.valueobjects.ZoneType;

public record CreateZoneCommand(Long storeId, String name, ZoneType type, Integer capacity) {
}
