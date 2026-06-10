package com.retailpulse.platform.assistedshopping.interfaces.rest.resources;

import com.retailpulse.platform.assistedshopping.domain.model.valueobjects.SessionStatus;

public record KioskSessionResource(Long id, Long storeId, SessionStatus status) {
}
