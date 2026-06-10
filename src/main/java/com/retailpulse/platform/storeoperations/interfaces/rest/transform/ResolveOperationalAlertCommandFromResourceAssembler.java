package com.retailpulse.platform.storeoperations.interfaces.rest.transform;

import com.retailpulse.platform.storeoperations.domain.model.commands.ResolveOperationalAlertCommand;

public class ResolveOperationalAlertCommandFromResourceAssembler {
    public static ResolveOperationalAlertCommand toCommand(Long alertId) { return new ResolveOperationalAlertCommand(alertId); }
}
