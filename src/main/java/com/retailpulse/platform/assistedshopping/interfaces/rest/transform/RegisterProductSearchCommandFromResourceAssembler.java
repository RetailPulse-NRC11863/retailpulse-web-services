package com.retailpulse.platform.assistedshopping.interfaces.rest.transform;

import com.retailpulse.platform.assistedshopping.domain.model.commands.RegisterProductSearchCommand;
import com.retailpulse.platform.assistedshopping.interfaces.rest.resources.RegisterProductSearchResource;

public class RegisterProductSearchCommandFromResourceAssembler {
    public static RegisterProductSearchCommand toCommand(Long sessionId, RegisterProductSearchResource resource) { return new RegisterProductSearchCommand(sessionId, resource.query(), resource.productId(), resource.action()); }
}
