package com.retailpulse.platform.assistedshopping.infrastructure.persistence.jpa.repositories;

import com.retailpulse.platform.assistedshopping.infrastructure.persistence.jpa.entities.ProductSearchPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductSearchPersistenceRepository extends JpaRepository<ProductSearchPersistenceEntity, Long> {
    List<ProductSearchPersistenceEntity> findByProductId(Long productId);
}
