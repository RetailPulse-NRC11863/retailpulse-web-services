package com.retailpulse.platform.storefoundation.application.internal.queryservices;

import com.retailpulse.platform.storefoundation.application.queryservices.ZoneQueryService;
import com.retailpulse.platform.storefoundation.domain.model.entities.Zone;
import com.retailpulse.platform.storefoundation.domain.repositories.ZoneRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ZoneQueryServiceImpl implements ZoneQueryService {
    private final ZoneRepository repository;
    public ZoneQueryServiceImpl(ZoneRepository repository) { this.repository = repository; }
    public List<Zone> handleGetAll() { return repository.findAll(); }
    public Optional<Zone> handleGetById(Long zoneId) { return repository.findById(zoneId); }
}
