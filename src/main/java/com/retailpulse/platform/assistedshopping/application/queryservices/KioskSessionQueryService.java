package com.retailpulse.platform.assistedshopping.application.queryservices;

import com.retailpulse.platform.assistedshopping.domain.model.aggregates.KioskSession;
import com.retailpulse.platform.storefoundation.domain.model.aggregates.Product;
import java.util.List;
import java.util.Optional;

public interface KioskSessionQueryService {
    List<Product> searchProducts(String query);
    Optional<Product> getProductById(Long productId);
    Optional<KioskSession> getSessionById(Long sessionId);
}
