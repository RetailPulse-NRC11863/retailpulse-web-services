package com.retailpulse.platform.storeoperations.application.commandservices;

import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalTask;
import com.retailpulse.platform.storeoperations.domain.model.commands.CreateOperationalTaskCommand;
import com.retailpulse.platform.storeoperations.domain.model.commands.CompleteOperationalTaskCommand;

public interface OperationalTaskCommandService {
    OperationalTask handle(CreateOperationalTaskCommand command);
    OperationalTask handle(CompleteOperationalTaskCommand command);
}
