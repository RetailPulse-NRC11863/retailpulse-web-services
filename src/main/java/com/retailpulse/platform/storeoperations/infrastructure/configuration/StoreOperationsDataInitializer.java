package com.retailpulse.platform.storeoperations.infrastructure.configuration;

import com.retailpulse.platform.storeoperations.application.commandservices.OperationalAlertCommandService;
import com.retailpulse.platform.storeoperations.application.commandservices.OperationalTaskCommandService;
import com.retailpulse.platform.storeoperations.domain.model.commands.CreateOperationalAlertCommand;
import com.retailpulse.platform.storeoperations.domain.model.commands.CreateOperationalTaskCommand;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertStatus;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertType;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.PriorityLevel;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.TaskStatus;
import com.retailpulse.platform.storeoperations.domain.repositories.OperationalAlertRepository;
import com.retailpulse.platform.storeoperations.domain.repositories.OperationalTaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import java.time.Instant;
import java.util.List;

@Configuration
@Profile("dev")
public class StoreOperationsDataInitializer {
    @Bean
    CommandLineRunner seedStoreOperationsData(
        OperationalAlertCommandService alertCommandService,
        OperationalTaskCommandService taskCommandService,
        OperationalAlertRepository alertRepository,
        OperationalTaskRepository taskRepository
    ) {
        return args -> {
            for (CreateOperationalAlertCommand command : alerts()) {
                if (!alertRepository.existsById(command.id())) {
                    alertCommandService.handle(command);
                }
            }
            for (CreateOperationalTaskCommand command : tasks()) {
                if (!taskRepository.existsById(command.id())) {
                    taskCommandService.handle(command);
                }
            }
        };
    }

    private List<CreateOperationalAlertCommand> alerts() {
        return List.of(
            new CreateOperationalAlertCommand("A001", AlertType.OUT_OF_STOCK, PriorityLevel.HIGH, AlertStatus.PENDING, "Primor Oil 1L out of stock in Aisle 2", "Z002", "Aisle 2", "P002", "Primor Oil 1L", Instant.parse("2026-05-04T10:00:00Z")),
            new CreateOperationalAlertCommand("A002", AlertType.LOW_STOCK, PriorityLevel.MEDIUM, AlertStatus.IN_PROGRESS, "Costeño Rice 5kg low stock (18 units)", "Z002", "Aisle 2", "P001", "Costeño Rice 5kg", Instant.parse("2026-05-04T11:30:00Z"))
        );
    }

    private List<CreateOperationalTaskCommand> tasks() {
        return List.of(
            new CreateOperationalTaskCommand("T001", "Restock Primor Oil", "Bring inventory from main warehouse for Primor Oil 1L and stock Shelf B1.", PriorityLevel.HIGH, TaskStatus.PENDING, "Z002", "Aisle 2", "A001", Instant.parse("2026-05-04T10:05:00Z")),
            new CreateOperationalTaskCommand("T002", "Check Rice stock", "Check if there are more units of Costeño Rice 5kg in backroom.", PriorityLevel.MEDIUM, TaskStatus.IN_PROGRESS, "Z002", "Aisle 2", "A002", Instant.parse("2026-05-04T11:35:00Z"))
        );
    }
}
