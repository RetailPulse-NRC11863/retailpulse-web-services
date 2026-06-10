package com.retailpulse.platform.storeoperations.application.queryservices;

import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalAlert;
import java.util.List;

public interface OperationalAlertQueryService {
    List<OperationalAlert> getAll();
    List<OperationalAlert> getActive();
}
