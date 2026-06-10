package com.retailpulse.platform.storefoundation.application.commandservices;

import com.retailpulse.platform.storefoundation.domain.model.entities.Zone;
import com.retailpulse.platform.storefoundation.domain.model.commands.CreateZoneCommand;
import com.retailpulse.platform.storefoundation.domain.model.commands.UpdateZoneCommand;

public interface ZoneCommandService {
    Zone handle(CreateZoneCommand command);
    Zone handle(UpdateZoneCommand command);
    void delete(Long zoneId);
}
