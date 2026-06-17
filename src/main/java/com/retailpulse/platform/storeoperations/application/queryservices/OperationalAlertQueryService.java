package com.retailpulse.platform.storeoperations.application.queryservices;

import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalAlert;
import com.retailpulse.platform.storeoperations.domain.model.queries.GetActiveOperationalAlertsQuery;
import com.retailpulse.platform.storeoperations.domain.model.queries.GetAllOperationalAlertsQuery;
import com.retailpulse.platform.storeoperations.domain.model.queries.GetOperationalAlertByIdQuery;
import java.util.List;
import java.util.Optional;

public interface OperationalAlertQueryService {
    List<OperationalAlert> handle(GetAllOperationalAlertsQuery query);
    Optional<OperationalAlert> handle(GetOperationalAlertByIdQuery query);
    List<OperationalAlert> handle(GetActiveOperationalAlertsQuery query);
}
