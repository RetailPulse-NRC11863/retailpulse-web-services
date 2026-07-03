package com.retailpulse.platform.storefoundation.domain.model.commands;

public record CreateStoreCommand(String name, String address, String managerName, String status) {
}
