package com.retailpulse.platform.storefoundation.application.commandservices;

import com.retailpulse.platform.storefoundation.domain.model.aggregates.Product;
import com.retailpulse.platform.storefoundation.domain.model.commands.CreateProductCommand;
import com.retailpulse.platform.storefoundation.domain.model.commands.UpdateProductCommand;

public interface ProductCommandService {
    Product handle(CreateProductCommand command);
    Product handle(UpdateProductCommand command);
    void delete(Long productId);
}
