package com.retailpulse.platform.storefoundation.interfaces.rest.resources;

import com.retailpulse.platform.storefoundation.domain.model.valueobjects.ZoneType;

public record ZoneResource(Long id, Long storeId, String name, ZoneType type, Integer capacity, Integer x, Integer y, Integer width, Integer height) {
}
