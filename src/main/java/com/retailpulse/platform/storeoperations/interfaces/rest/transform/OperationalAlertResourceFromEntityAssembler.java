package com.retailpulse.platform.storeoperations.interfaces.rest.transform;

import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalAlert;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.OperationalAlertResource;

public class OperationalAlertResourceFromEntityAssembler {
    public static OperationalAlertResource toResource(OperationalAlert alert) {
        return new OperationalAlertResource(
            alert.getId(),
            alert.getType(),
            alert.getPriority(),
            alert.getStatus(),
            alert.getMessage(),
            alert.getZoneId(),
            alert.getZoneName(),
            alert.getProductId(),
            alert.getProductName(),
            alert.getCreatedAt()
        );
    }
}
