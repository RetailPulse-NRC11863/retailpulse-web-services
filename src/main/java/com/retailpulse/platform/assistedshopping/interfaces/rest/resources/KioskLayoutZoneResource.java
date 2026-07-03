package com.retailpulse.platform.assistedshopping.interfaces.rest.resources;

public record KioskLayoutZoneResource(
    Long id,
    String name,
    String type,
    Integer x,
    Integer y,
    Integer width,
    Integer height
) {
}
