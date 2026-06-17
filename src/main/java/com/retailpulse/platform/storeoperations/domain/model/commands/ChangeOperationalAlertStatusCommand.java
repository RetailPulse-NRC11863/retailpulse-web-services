package com.retailpulse.platform.storeoperations.domain.model.commands;

import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertStatus;

public record ChangeOperationalAlertStatusCommand(String id, AlertStatus status) {
}
