package com.retailpulse.platform.inventoryintelligence.application.commandservices;

import com.retailpulse.platform.inventoryintelligence.domain.model.aggregates.Product;
import com.retailpulse.platform.inventoryintelligence.domain.model.commands.CreateProductCommand;
import com.retailpulse.platform.inventoryintelligence.domain.model.commands.DeleteProductCommand;
import com.retailpulse.platform.inventoryintelligence.domain.model.commands.UpdateProductCommand;
import com.retailpulse.platform.shared.application.result.Result;

public interface ProductCommandService {
    Result<Product> handle(CreateProductCommand command);
    Result<Product> handle(UpdateProductCommand command);
    Result<Void> handle(DeleteProductCommand command);
}
