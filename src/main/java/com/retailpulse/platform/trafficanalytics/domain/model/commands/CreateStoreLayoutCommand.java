package com.retailpulse.platform.trafficanalytics.domain.model.commands;

public record CreateStoreLayoutCommand(String id, String name, String storeName, Integer width, Integer height, Boolean active) {
}
