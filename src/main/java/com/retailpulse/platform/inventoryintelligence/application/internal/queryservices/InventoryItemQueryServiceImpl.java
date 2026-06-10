package com.retailpulse.platform.inventoryintelligence.application.internal.queryservices;

import com.retailpulse.platform.inventoryintelligence.application.queryservices.InventoryItemQueryService;
import com.retailpulse.platform.inventoryintelligence.domain.model.aggregates.InventoryItem;
import com.retailpulse.platform.inventoryintelligence.domain.repositories.InventoryItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryItemQueryServiceImpl implements InventoryItemQueryService {
    private final InventoryItemRepository repository;
    public InventoryItemQueryServiceImpl(InventoryItemRepository repository) { this.repository = repository; }
    public List<InventoryItem> handleGetAll() { return repository.findAll(); }
    public Optional<InventoryItem> handleGetByProductId(Long productId) { return repository.findByProductId(productId); }
    public List<InventoryItem> handleGetCritical() { return repository.findCriticalItems(); }
}
