package com.retailpulse.platform.trafficanalytics.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;

public record RegisterMovementEventResource(@NotNull Long zoneId, @NotBlank String eventType, OffsetDateTime occurredAt) {
}
