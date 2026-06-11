package com.retailpulse.platform.trafficanalytics.domain.model.commands;

public record UpdateStoreLayoutCommand(String id, String name, String storeName, Integer width, Integer height, Boolean active) {
}
