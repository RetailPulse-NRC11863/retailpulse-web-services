package com.retailpulse.platform.trafficanalytics.application.commandservices;

import com.retailpulse.platform.shared.application.result.Result;
import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.StoreLayout;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.CreateStoreLayoutCommand;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.UpdateStoreLayoutCommand;

public interface StoreLayoutCommandService {
    Result<StoreLayout> handle(CreateStoreLayoutCommand command);
    Result<StoreLayout> handle(UpdateStoreLayoutCommand command);
}
