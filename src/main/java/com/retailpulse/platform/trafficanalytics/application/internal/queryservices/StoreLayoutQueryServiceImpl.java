package com.retailpulse.platform.trafficanalytics.application.internal.queryservices;

import com.retailpulse.platform.trafficanalytics.application.queryservices.StoreLayoutQueryService;
import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.StoreLayout;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetAllStoreLayoutsQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetCurrentStoreLayoutQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetStoreLayoutByIdQuery;
import com.retailpulse.platform.trafficanalytics.domain.repositories.StoreLayoutRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StoreLayoutQueryServiceImpl implements StoreLayoutQueryService {
    private final StoreLayoutRepository repository;

    public StoreLayoutQueryServiceImpl(StoreLayoutRepository repository) {
        this.repository = repository;
    }

    public List<StoreLayout> handle(GetAllStoreLayoutsQuery query) {
        return repository.findAll();
    }

    public Optional<StoreLayout> handle(GetStoreLayoutByIdQuery query) {
        return repository.findById(query.id());
    }

    public Optional<StoreLayout> handle(GetCurrentStoreLayoutQuery query) {
        return repository.findCurrent();
    }
}
