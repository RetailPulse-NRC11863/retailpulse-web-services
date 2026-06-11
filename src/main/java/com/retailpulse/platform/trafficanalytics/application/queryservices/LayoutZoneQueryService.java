package com.retailpulse.platform.trafficanalytics.application.queryservices;

import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.LayoutZone;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetAllLayoutZonesQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetLayoutZoneByIdQuery;
import java.util.List;
import java.util.Optional;

public interface LayoutZoneQueryService {
    List<LayoutZone> handle(GetAllLayoutZonesQuery query);
    Optional<LayoutZone> handle(GetLayoutZoneByIdQuery query);
}
