package com.retailpulse.platform.assistedshopping.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

public record RegisterProductSearchResource(@NotBlank String query, Long productId, String action) {
}
