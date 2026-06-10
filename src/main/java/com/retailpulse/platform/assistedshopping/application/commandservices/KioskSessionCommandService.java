package com.retailpulse.platform.assistedshopping.application.commandservices;

import com.retailpulse.platform.assistedshopping.domain.model.aggregates.KioskSession;
import com.retailpulse.platform.assistedshopping.domain.model.entities.ProductSearch;
import com.retailpulse.platform.assistedshopping.domain.model.commands.StartKioskSessionCommand;
import com.retailpulse.platform.assistedshopping.domain.model.commands.RegisterProductSearchCommand;

public interface KioskSessionCommandService {
    KioskSession handle(StartKioskSessionCommand command);
    ProductSearch handle(RegisterProductSearchCommand command);
}
