package com.retailpulse.platform.assistedshopping.interfaces.rest.resources;

import java.math.BigDecimal;
import java.util.List;

public record KioskProductResource(
    Long id,
    Long storeId,
    String name,
    String category,
    BigDecimal price,
    Long zoneId,
    String zoneName,
    Integer stock,
    String stockStatus,
    String aisle,
    String shelf,
    String shelfReference,
    String displayReference,
    String promotion,
    Integer zoneX,
    Integer zoneY,
    Integer zoneWidth,
    Integer zoneHeight,
    List<KioskLayoutZoneResource> layoutZones
) {
}
