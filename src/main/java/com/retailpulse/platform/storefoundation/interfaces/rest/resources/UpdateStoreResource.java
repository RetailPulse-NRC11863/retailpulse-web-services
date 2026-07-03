package com.retailpulse.platform.storefoundation.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

public record UpdateStoreResource(@NotBlank String name, @NotBlank String address, String managerName, String status) {
}
