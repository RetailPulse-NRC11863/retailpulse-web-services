package com.retailpulse.platform.trafficanalytics.application.commandservices;

import com.retailpulse.platform.shared.application.result.Result;
import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.LayoutZone;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.CreateLayoutZoneCommand;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.UpdateLayoutZoneCommand;

public interface LayoutZoneCommandService {
    Result<LayoutZone> handle(CreateLayoutZoneCommand command);
    Result<LayoutZone> handle(UpdateLayoutZoneCommand command);
}
