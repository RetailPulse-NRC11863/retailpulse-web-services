package com.retailpulse.platform.trafficanalytics.application.queryservices;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.StoreLayout;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetAllStoreLayoutsQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetCurrentStoreLayoutQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetStoreLayoutByIdQuery;
import java.util.List;
import java.util.Optional;

public interface StoreLayoutQueryService {
    List<StoreLayout> handle(GetAllStoreLayoutsQuery query);
    Optional<StoreLayout> handle(GetStoreLayoutByIdQuery query);
    Optional<StoreLayout> handle(GetCurrentStoreLayoutQuery query);
}
