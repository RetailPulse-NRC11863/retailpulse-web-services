package com.retailpulse.platform.assistedshopping.interfaces.rest.transform;

import com.retailpulse.platform.assistedshopping.domain.model.aggregates.KioskSession;
import com.retailpulse.platform.assistedshopping.interfaces.rest.resources.KioskSessionResource;

public class KioskSessionResourceFromEntityAssembler {
    public static KioskSessionResource toResource(KioskSession session) { return new KioskSessionResource(session.getId(), session.getStoreId(), session.getStatus()); }
}
