package com.retailpulse.platform.storeoperations.interfaces.rest.transform;

import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalTask;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.OperationalTaskResource;

public class OperationalTaskResourceFromEntityAssembler {
    public static OperationalTaskResource toResource(OperationalTask task) {
        return new OperationalTaskResource(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getPriority(),
            task.getStatus(),
            task.getZoneId(),
            task.getZoneName(),
            task.getAlertId(),
            task.getCreatedAt()
        );
    }
}
