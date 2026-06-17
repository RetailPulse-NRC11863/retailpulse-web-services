package com.retailpulse.platform.storeoperations.application.commandservices;

import com.retailpulse.platform.shared.application.result.Result;
import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalTask;
import com.retailpulse.platform.storeoperations.domain.model.commands.ChangeOperationalTaskStatusCommand;
import com.retailpulse.platform.storeoperations.domain.model.commands.CreateOperationalTaskCommand;
import com.retailpulse.platform.storeoperations.domain.model.commands.UpdateOperationalTaskCommand;

public interface OperationalTaskCommandService {
    Result<OperationalTask> handle(CreateOperationalTaskCommand command);
    Result<OperationalTask> handle(UpdateOperationalTaskCommand command);
    Result<OperationalTask> handle(ChangeOperationalTaskStatusCommand command);
}
