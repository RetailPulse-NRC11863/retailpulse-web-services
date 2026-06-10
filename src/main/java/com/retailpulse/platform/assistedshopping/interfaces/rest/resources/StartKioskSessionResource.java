package com.retailpulse.platform.assistedshopping.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

public record StartKioskSessionResource(@NotNull Long storeId) {
}
