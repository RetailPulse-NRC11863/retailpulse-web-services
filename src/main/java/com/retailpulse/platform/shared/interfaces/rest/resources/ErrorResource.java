package com.retailpulse.platform.shared.interfaces.rest.resources;

import java.time.OffsetDateTime;
import java.util.List;

public record ErrorResource(String code, String message, List<String> details, OffsetDateTime timestamp) {
}
