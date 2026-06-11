package com.retailpulse.platform.trafficanalytics.application.internal.queryservices;

import com.retailpulse.platform.trafficanalytics.application.queryservices.LayoutZoneQueryService;
import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.LayoutZone;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetAllLayoutZonesQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetLayoutZoneByIdQuery;
import com.retailpulse.platform.trafficanalytics.domain.repositories.LayoutZoneRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LayoutZoneQueryServiceImpl implements LayoutZoneQueryService {
    private final LayoutZoneRepository repository;

    public LayoutZoneQueryServiceImpl(LayoutZoneRepository repository) {
        this.repository = repository;
    }

    public List<LayoutZone> handle(GetAllLayoutZonesQuery query) {
        return repository.findAll();
    }

    public Optional<LayoutZone> handle(GetLayoutZoneByIdQuery query) {
        return repository.findById(query.id());
    }
}
