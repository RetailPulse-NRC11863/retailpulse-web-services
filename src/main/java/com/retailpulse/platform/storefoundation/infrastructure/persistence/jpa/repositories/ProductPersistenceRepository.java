package com.retailpulse.platform.storefoundation.infrastructure.persistence.jpa.repositories;

import com.retailpulse.platform.storefoundation.infrastructure.persistence.jpa.entities.ProductPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductPersistenceRepository extends JpaRepository<ProductPersistenceEntity, Long> {

    List<ProductPersistenceEntity> findByNameContainingIgnoreCaseOrSkuContainingIgnoreCase(String name, String sku);

}
