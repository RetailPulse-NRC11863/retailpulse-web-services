package com.retailpulse.platform.storeoperations.application.commandservices;

import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalAlert;
import com.retailpulse.platform.storeoperations.domain.model.commands.CreateOperationalAlertCommand;
import com.retailpulse.platform.storeoperations.domain.model.commands.ResolveOperationalAlertCommand;

public interface OperationalAlertCommandService {
    OperationalAlert handle(CreateOperationalAlertCommand command);
    OperationalAlert handle(ResolveOperationalAlertCommand command);
}
