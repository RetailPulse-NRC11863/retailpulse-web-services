package com.retailpulse.platform.storeoperations.application.commandservices;

import com.retailpulse.platform.shared.application.result.Result;
import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalAlert;
import com.retailpulse.platform.storeoperations.domain.model.commands.ChangeOperationalAlertStatusCommand;
import com.retailpulse.platform.storeoperations.domain.model.commands.CreateOperationalAlertCommand;
import com.retailpulse.platform.storeoperations.domain.model.commands.UpdateOperationalAlertCommand;

public interface OperationalAlertCommandService {
    Result<OperationalAlert> handle(CreateOperationalAlertCommand command);
    Result<OperationalAlert> handle(UpdateOperationalAlertCommand command);
    Result<OperationalAlert> handle(ChangeOperationalAlertStatusCommand command);
}
