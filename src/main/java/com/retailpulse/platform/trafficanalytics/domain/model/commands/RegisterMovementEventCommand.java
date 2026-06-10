package com.retailpulse.platform.trafficanalytics.domain.model.commands;

import java.time.OffsetDateTime;

public record RegisterMovementEventCommand(Long zoneId, String eventType, OffsetDateTime occurredAt) {
}
