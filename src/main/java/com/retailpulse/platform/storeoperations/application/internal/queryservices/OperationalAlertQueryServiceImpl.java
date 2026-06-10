package com.retailpulse.platform.storeoperations.application.internal.queryservices;

import com.retailpulse.platform.storeoperations.application.queryservices.OperationalAlertQueryService;
import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalAlert;
import com.retailpulse.platform.storeoperations.domain.repositories.OperationalAlertRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OperationalAlertQueryServiceImpl implements OperationalAlertQueryService {
    private final OperationalAlertRepository repository;
    public OperationalAlertQueryServiceImpl(OperationalAlertRepository repository) { this.repository = repository; }
    public List<OperationalAlert> getAll() { return repository.findAll(); }
    public List<OperationalAlert> getActive() { return repository.findActive(); }
}
