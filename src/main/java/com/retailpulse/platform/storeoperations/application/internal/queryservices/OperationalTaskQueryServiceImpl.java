package com.retailpulse.platform.storeoperations.application.internal.queryservices;

import com.retailpulse.platform.storeoperations.application.queryservices.OperationalTaskQueryService;
import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalTask;
import com.retailpulse.platform.storeoperations.domain.model.queries.GetAllOperationalTasksQuery;
import com.retailpulse.platform.storeoperations.domain.model.queries.GetOperationalTaskByIdQuery;
import com.retailpulse.platform.storeoperations.domain.model.queries.GetPendingOperationalTasksQuery;
import com.retailpulse.platform.storeoperations.domain.repositories.OperationalTaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OperationalTaskQueryServiceImpl implements OperationalTaskQueryService {
    private final OperationalTaskRepository repository;

    public OperationalTaskQueryServiceImpl(OperationalTaskRepository repository) {
        this.repository = repository;
    }

    public List<OperationalTask> handle(GetAllOperationalTasksQuery query) {
        return repository.findAll();
    }

    public Optional<OperationalTask> handle(GetOperationalTaskByIdQuery query) {
        return repository.findById(query.id());
    }

    public List<OperationalTask> handle(GetPendingOperationalTasksQuery query) {
        return repository.findPending();
    }
}
