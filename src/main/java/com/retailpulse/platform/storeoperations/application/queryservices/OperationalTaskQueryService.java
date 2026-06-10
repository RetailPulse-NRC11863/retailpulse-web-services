package com.retailpulse.platform.storeoperations.application.queryservices;

import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalTask;
import java.util.List;

public interface OperationalTaskQueryService {
    List<OperationalTask> getAll();
    List<OperationalTask> getPending();
}
