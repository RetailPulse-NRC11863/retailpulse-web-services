package com.retailpulse.platform.storeoperations.application.internal.queryservices;

import com.retailpulse.platform.storeoperations.application.queryservices.OperationalAlertQueryService;
import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalAlert;
import com.retailpulse.platform.storeoperations.domain.model.queries.GetActiveOperationalAlertsQuery;
import com.retailpulse.platform.storeoperations.domain.model.queries.GetAllOperationalAlertsQuery;
import com.retailpulse.platform.storeoperations.domain.model.queries.GetOperationalAlertByIdQuery;
import com.retailpulse.platform.storeoperations.domain.repositories.OperationalAlertRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OperationalAlertQueryServiceImpl implements OperationalAlertQueryService {
    private final OperationalAlertRepository repository;

    public OperationalAlertQueryServiceImpl(OperationalAlertRepository repository) {
        this.repository = repository;
    }

    public List<OperationalAlert> handle(GetAllOperationalAlertsQuery query) {
        return repository.findAll();
    }

    public Optional<OperationalAlert> handle(GetOperationalAlertByIdQuery query) {
        return repository.findById(query.id());
    }

    public List<OperationalAlert> handle(GetActiveOperationalAlertsQuery query) {
        return repository.findActive();
    }
}
