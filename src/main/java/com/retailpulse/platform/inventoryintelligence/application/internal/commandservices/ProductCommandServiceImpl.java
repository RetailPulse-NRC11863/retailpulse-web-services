package com.retailpulse.platform.inventoryintelligence.application.internal.commandservices;

import com.retailpulse.platform.inventoryintelligence.application.commandservices.ProductCommandService;
import com.retailpulse.platform.inventoryintelligence.domain.model.aggregates.Product;
import com.retailpulse.platform.inventoryintelligence.domain.model.commands.CreateProductCommand;
import com.retailpulse.platform.inventoryintelligence.domain.model.commands.DeleteProductCommand;
import com.retailpulse.platform.inventoryintelligence.domain.model.commands.UpdateProductCommand;
import com.retailpulse.platform.inventoryintelligence.domain.repositories.ProductRepository;
import com.retailpulse.platform.shared.application.result.ApplicationError;
import com.retailpulse.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {
    private final ProductRepository repository;

    public ProductCommandServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    public Result<Product> handle(CreateProductCommand command) {
        if (repository.existsById(command.id())) {
            return Result.failure(new ApplicationError("conflict", "Product already exists"));
        }
        try {
            Product product = new Product(command.id(), command.name(), command.category(), command.price(), command.stock(), command.zoneName(), command.shelfReference(), command.promotion());
            return Result.success(repository.save(product));
        } catch (IllegalArgumentException exception) {
            return Result.failure(ApplicationError.validation(exception.getMessage()));
        }
    }

    public Result<Product> handle(UpdateProductCommand command) {
        return repository.findById(command.id())
            .map(product -> updateProduct(command, product))
            .orElseGet(() -> Result.failure(ApplicationError.notFound("Product not found")));
    }

    public Result<Void> handle(DeleteProductCommand command) {
        if (!repository.existsById(command.id())) {
            return Result.failure(ApplicationError.notFound("Product not found"));
        }
        repository.deleteById(command.id());
        return Result.success(null);
    }

    private Result<Product> updateProduct(UpdateProductCommand command, Product product) {
        try {
            product.updateInformation(command.name(), command.category(), command.price(), command.stock(), command.zoneName(), command.shelfReference(), command.promotion());
            return Result.success(repository.save(product));
        } catch (IllegalArgumentException exception) {
            return Result.failure(ApplicationError.validation(exception.getMessage()));
        }
    }
}
