package com.retailpulse.platform.assistedshopping.domain.model.commands;

public record RegisterProductSearchCommand(Long sessionId, String query, Long productId, String action) {
}
