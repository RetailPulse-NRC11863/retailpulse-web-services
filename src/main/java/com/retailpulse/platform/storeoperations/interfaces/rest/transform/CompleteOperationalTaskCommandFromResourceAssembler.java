package com.retailpulse.platform.storeoperations.interfaces.rest.transform;

import com.retailpulse.platform.storeoperations.domain.model.commands.CompleteOperationalTaskCommand;

public class CompleteOperationalTaskCommandFromResourceAssembler {
    public static CompleteOperationalTaskCommand toCommand(Long taskId) { return new CompleteOperationalTaskCommand(taskId); }
}
