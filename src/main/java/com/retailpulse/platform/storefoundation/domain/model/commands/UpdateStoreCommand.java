package com.retailpulse.platform.storefoundation.domain.model.commands;

public record UpdateStoreCommand(Long storeId, String name, String address, String managerName, String status) {
}
