package com.retailpulse.platform.assistedshopping.interfaces.rest.transform;

import com.retailpulse.platform.assistedshopping.domain.model.commands.StartKioskSessionCommand;
import com.retailpulse.platform.assistedshopping.interfaces.rest.resources.StartKioskSessionResource;

public class StartKioskSessionCommandFromResourceAssembler {
    public static StartKioskSessionCommand toCommand(StartKioskSessionResource resource) { return new StartKioskSessionCommand(resource.storeId()); }
}
