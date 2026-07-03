package com.retailpulse.platform.storeoperations.interfaces.rest.transform;

import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalAlert;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.OperationalAlertResource;

public class OperationalAlertResourceFromEntityAssembler {
    public static OperationalAlertResource toResource(OperationalAlert a) { return new OperationalAlertResource(a.getId(), a.getStoreId(), a.getTitle(), a.getDescription(), a.getType(), a.getStatus(), a.getPriority(), a.getProductId(), a.getZoneId(), a.getSource(), a.getTriggerReason()); }
}
