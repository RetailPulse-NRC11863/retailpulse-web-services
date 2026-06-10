package com.retailpulse.platform.storefoundation.application.internal.commandservices;

import com.retailpulse.platform.storefoundation.application.commandservices.ProductCommandService;
import com.retailpulse.platform.storefoundation.domain.model.aggregates.Product;
import com.retailpulse.platform.storefoundation.domain.model.commands.CreateProductCommand;
import com.retailpulse.platform.storefoundation.domain.model.commands.UpdateProductCommand;
import com.retailpulse.platform.storefoundation.domain.model.valueobjects.ProductStatus;
import com.retailpulse.platform.storefoundation.domain.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {
    private final ProductRepository repository;
    public ProductCommandServiceImpl(ProductRepository repository) { this.repository = repository; }
    public Product handle(CreateProductCommand command) {
        return repository.save(new Product(null, command.storeId(), command.name(), command.sku(), command.category(), command.description(), command.price(), ProductStatus.ACTIVE, command.zoneId(), command.location()));
    }
    public Product handle(UpdateProductCommand command) {
        Product product = repository.findById(command.productId()).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.update(command.name(), command.category(), command.description(), command.price(), command.status(), command.zoneId(), command.location());
        return repository.save(product);
    }
    public void delete(Long productId) { repository.deleteById(productId); }
}
