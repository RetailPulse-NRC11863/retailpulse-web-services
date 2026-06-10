package com.retailpulse.platform.storefoundation.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

public record CreateStoreResource(@NotBlank String name, @NotBlank String address) {
}
