package com.retailpulse.platform.storeoperations.application.internal.queryservices;

import com.retailpulse.platform.storeoperations.application.queryservices.OperationalTaskQueryService;
import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalTask;
import com.retailpulse.platform.storeoperations.domain.repositories.OperationalTaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OperationalTaskQueryServiceImpl implements OperationalTaskQueryService {
    private final OperationalTaskRepository repository;
    public OperationalTaskQueryServiceImpl(OperationalTaskRepository repository) { this.repository = repository; }
    public List<OperationalTask> getAll() { return repository.findAll(); }
    public List<OperationalTask> getPending() { return repository.findPending(); }
}
