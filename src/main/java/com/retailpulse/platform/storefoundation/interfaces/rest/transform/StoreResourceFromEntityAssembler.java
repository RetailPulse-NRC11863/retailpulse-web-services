package com.retailpulse.platform.storefoundation.interfaces.rest.transform;

import com.retailpulse.platform.storefoundation.domain.model.aggregates.Store;
import com.retailpulse.platform.storefoundation.interfaces.rest.resources.StoreResource;

public class StoreResourceFromEntityAssembler {
    public static StoreResource toResource(Store store) { return new StoreResource(store.getId(), store.getName(), store.getAddress()); }
}
