package com.retailpulse.platform.inventoryintelligence.infrastructure.configuration;

import com.retailpulse.platform.inventoryintelligence.application.commandservices.ProductCommandService;
import com.retailpulse.platform.inventoryintelligence.domain.model.commands.CreateProductCommand;
import com.retailpulse.platform.inventoryintelligence.domain.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import java.math.BigDecimal;
import java.util.List;

@Configuration
@Profile("dev")
public class InventoryIntelligenceDataInitializer {
    @Bean
    CommandLineRunner seedInventoryIntelligenceProducts(ProductCommandService productCommandService, ProductRepository productRepository) {
        return args -> {
            for (CreateProductCommand command : products()) {
                if (!productRepository.existsById(command.id())) {
                    productCommandService.handle(command);
                }
            }
        };
    }

    private List<CreateProductCommand> products() {
        return List.of(
            new CreateProductCommand("P001", "Costeño Rice 5kg", "Groceries", new BigDecimal("24.9"), 18, "Aisle 2", "Shelf B3", "10% off"),
            new CreateProductCommand("P002", "Primor Oil 1L", "Groceries", new BigDecimal("8.5"), 0, "Aisle 2", "Shelf B1", null),
            new CreateProductCommand("P003", "Gloria Evaporated Milk", "Dairy", new BigDecimal("3.8"), 120, "Aisle 4", "Shelf A1", "3x2")
        );
    }
}
