package com.retailpulse.platform.assistedshopping.application.internal.queryservices;

import com.retailpulse.platform.assistedshopping.application.outboundservices.acl.ExternalProductCatalogService;
import com.retailpulse.platform.assistedshopping.application.queryservices.KioskSessionQueryService;
import com.retailpulse.platform.assistedshopping.domain.model.aggregates.KioskSession;
import com.retailpulse.platform.assistedshopping.domain.repositories.KioskSessionRepository;
import com.retailpulse.platform.storefoundation.domain.model.aggregates.Product;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class KioskSessionQueryServiceImpl implements KioskSessionQueryService {
    private final KioskSessionRepository repository;
    private final ExternalProductCatalogService productCatalogService;
    public KioskSessionQueryServiceImpl(KioskSessionRepository repository, ExternalProductCatalogService productCatalogService) { this.repository = repository; this.productCatalogService = productCatalogService; }
    public List<Product> searchProducts(String query) { return productCatalogService.search(query); }
    public Optional<Product> getProductById(Long productId) { return productCatalogService.findById(productId); }
    public Optional<KioskSession> getSessionById(Long sessionId) { return repository.findById(sessionId); }
}
