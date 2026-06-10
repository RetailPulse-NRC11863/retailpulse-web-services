package com.retailpulse.platform.storeoperations.interfaces.rest.transform;

import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalTask;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.OperationalTaskResource;

public class OperationalTaskResourceFromEntityAssembler {
    public static OperationalTaskResource toResource(OperationalTask t) { return new OperationalTaskResource(t.getId(), t.getStoreId(), t.getTitle(), t.getDescription(), t.getStatus(), t.getPriority()); }
}
