package com.retailpulse.platform.inventoryintelligence.infrastructure.persistence.jpa.repositories;

import com.retailpulse.platform.inventoryintelligence.infrastructure.persistence.jpa.entities.InventoryItemPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

public interface InventoryItemPersistenceRepository extends JpaRepository<InventoryItemPersistenceEntity, Long> {

    Optional<InventoryItemPersistenceEntity> findByProductId(Long productId);
    @Query("select i from InventoryItemPersistenceEntity i where i.availableStock <= i.criticalThreshold")
    List<InventoryItemPersistenceEntity> findCriticalItems();

}
