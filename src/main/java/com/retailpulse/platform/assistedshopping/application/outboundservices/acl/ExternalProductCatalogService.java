package com.retailpulse.platform.assistedshopping.application.outboundservices.acl;

import com.retailpulse.platform.storefoundation.application.queryservices.ProductQueryService;
import com.retailpulse.platform.storefoundation.domain.model.aggregates.Product;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ExternalProductCatalogService {
    private final ProductQueryService productQueryService;
    public ExternalProductCatalogService(ProductQueryService productQueryService) { this.productQueryService = productQueryService; }
    public List<Product> search(String query) { return productQueryService.handleSearch(query); }
    public Optional<Product> findById(Long productId) { return productQueryService.handleGetById(productId); }
}
