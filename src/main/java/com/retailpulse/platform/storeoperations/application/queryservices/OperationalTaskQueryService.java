package com.retailpulse.platform.storeoperations.application.queryservices;

import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalTask;
import com.retailpulse.platform.storeoperations.domain.model.queries.GetAllOperationalTasksQuery;
import com.retailpulse.platform.storeoperations.domain.model.queries.GetOperationalTaskByIdQuery;
import com.retailpulse.platform.storeoperations.domain.model.queries.GetPendingOperationalTasksQuery;
import java.util.List;
import java.util.Optional;

public interface OperationalTaskQueryService {
    List<OperationalTask> handle(GetAllOperationalTasksQuery query);
    Optional<OperationalTask> handle(GetOperationalTaskByIdQuery query);
    List<OperationalTask> handle(GetPendingOperationalTasksQuery query);
}
