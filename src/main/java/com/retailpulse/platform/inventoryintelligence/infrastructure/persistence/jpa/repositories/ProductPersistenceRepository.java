package com.retailpulse.platform.inventoryintelligence.infrastructure.persistence.jpa.repositories;

import com.retailpulse.platform.inventoryintelligence.infrastructure.persistence.jpa.entities.ProductPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductPersistenceRepository extends JpaRepository<ProductPersistenceEntity, String> {
    List<ProductPersistenceEntity> findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrZoneNameContainingIgnoreCase(
        String name,
        String category,
        String zoneName
    );
}
