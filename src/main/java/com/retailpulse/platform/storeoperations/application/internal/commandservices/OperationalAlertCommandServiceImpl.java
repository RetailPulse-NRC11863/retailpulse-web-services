package com.retailpulse.platform.storeoperations.application.internal.commandservices;

import com.retailpulse.platform.shared.application.result.ApplicationError;
import com.retailpulse.platform.shared.application.result.Result;
import com.retailpulse.platform.storeoperations.application.commandservices.OperationalAlertCommandService;
import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalAlert;
import com.retailpulse.platform.storeoperations.domain.model.commands.ChangeOperationalAlertStatusCommand;
import com.retailpulse.platform.storeoperations.domain.model.commands.CreateOperationalAlertCommand;
import com.retailpulse.platform.storeoperations.domain.model.commands.UpdateOperationalAlertCommand;
import com.retailpulse.platform.storeoperations.domain.repositories.OperationalAlertRepository;
import org.springframework.stereotype.Service;

@Service
public class OperationalAlertCommandServiceImpl implements OperationalAlertCommandService {
    private final OperationalAlertRepository repository;

    public OperationalAlertCommandServiceImpl(OperationalAlertRepository repository) {
        this.repository = repository;
    }

    public Result<OperationalAlert> handle(CreateOperationalAlertCommand command) {
        if (repository.existsById(command.id())) return Result.failure(new ApplicationError("conflict", "Operational alert already exists"));
        try {
            OperationalAlert alert = new OperationalAlert(command.id(), command.type(), command.priority(), command.status(), command.message(), command.zoneId(), command.zoneName(), command.productId(), command.productName(), command.createdAt());
            return Result.success(repository.save(alert));
        } catch (IllegalArgumentException exception) {
            return Result.failure(ApplicationError.validation(exception.getMessage()));
        }
    }

    public Result<OperationalAlert> handle(UpdateOperationalAlertCommand command) {
        return repository.findById(command.id())
            .map(alert -> updateAlert(command, alert))
            .orElseGet(() -> Result.failure(ApplicationError.notFound("Operational alert not found")));
    }

    public Result<OperationalAlert> handle(ChangeOperationalAlertStatusCommand command) {
        return repository.findById(command.id())
            .map(alert -> changeStatus(command, alert))
            .orElseGet(() -> Result.failure(ApplicationError.notFound("Operational alert not found")));
    }

    private Result<OperationalAlert> updateAlert(UpdateOperationalAlertCommand command, OperationalAlert alert) {
        try {
            alert.updateInformation(command.type(), command.priority(), command.status(), command.message(), command.zoneId(), command.zoneName(), command.productId(), command.productName());
            return Result.success(repository.save(alert));
        } catch (IllegalArgumentException exception) {
            return Result.failure(ApplicationError.validation(exception.getMessage()));
        }
    }

    private Result<OperationalAlert> changeStatus(ChangeOperationalAlertStatusCommand command, OperationalAlert alert) {
        try {
            alert.changeStatus(command.status());
            return Result.success(repository.save(alert));
        } catch (IllegalArgumentException exception) {
            return Result.failure(ApplicationError.validation(exception.getMessage()));
        }
    }
}
