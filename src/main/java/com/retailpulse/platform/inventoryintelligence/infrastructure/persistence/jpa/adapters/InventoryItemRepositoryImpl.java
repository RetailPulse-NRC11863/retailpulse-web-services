package com.retailpulse.platform.inventoryintelligence.infrastructure.persistence.jpa.adapters;

import com.retailpulse.platform.inventoryintelligence.domain.model.aggregates.InventoryItem;
import com.retailpulse.platform.inventoryintelligence.domain.repositories.InventoryItemRepository;
import com.retailpulse.platform.inventoryintelligence.infrastructure.persistence.jpa.assemblers.InventoryItemPersistenceAssembler;
import com.retailpulse.platform.inventoryintelligence.infrastructure.persistence.jpa.repositories.InventoryItemPersistenceRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class InventoryItemRepositoryImpl implements InventoryItemRepository {
    private final InventoryItemPersistenceRepository repository;
    private final InventoryItemPersistenceAssembler assembler = new InventoryItemPersistenceAssembler();
    public InventoryItemRepositoryImpl(InventoryItemPersistenceRepository repository) { this.repository = repository; }
    public InventoryItem save(InventoryItem item) { return assembler.toDomain(repository.save(assembler.toEntity(item))); }
    public List<InventoryItem> findAll() { return repository.findAll().stream().map(assembler::toDomain).toList(); }
    public Optional<InventoryItem> findByProductId(Long productId) { return repository.findByProductId(productId).map(assembler::toDomain); }
    public List<InventoryItem> findCriticalItems() { return repository.findCriticalItems().stream().map(assembler::toDomain).toList(); }
    public long count() { return repository.count(); }
}
