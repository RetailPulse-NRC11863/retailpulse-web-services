package com.retailpulse.platform.assistedshopping.interfaces.rest.resources;

import java.math.BigDecimal;

public record KioskProductResource(Long id, String name, String category, BigDecimal price, Long zoneId) {
}
