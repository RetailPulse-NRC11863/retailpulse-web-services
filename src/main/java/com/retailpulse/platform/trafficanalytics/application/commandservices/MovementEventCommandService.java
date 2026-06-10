package com.retailpulse.platform.trafficanalytics.application.commandservices;

import com.retailpulse.platform.trafficanalytics.domain.model.entities.MovementEvent;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.RegisterMovementEventCommand;

public interface MovementEventCommandService {
    MovementEvent handle(RegisterMovementEventCommand command);
}
