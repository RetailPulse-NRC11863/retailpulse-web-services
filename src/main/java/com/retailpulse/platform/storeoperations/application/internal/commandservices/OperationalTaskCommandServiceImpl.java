package com.retailpulse.platform.storeoperations.application.internal.commandservices;

import com.retailpulse.platform.shared.application.result.ApplicationError;
import com.retailpulse.platform.shared.application.result.Result;
import com.retailpulse.platform.storeoperations.application.commandservices.OperationalTaskCommandService;
import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalTask;
import com.retailpulse.platform.storeoperations.domain.model.commands.ChangeOperationalTaskStatusCommand;
import com.retailpulse.platform.storeoperations.domain.model.commands.CreateOperationalTaskCommand;
import com.retailpulse.platform.storeoperations.domain.model.commands.UpdateOperationalTaskCommand;
import com.retailpulse.platform.storeoperations.domain.repositories.OperationalTaskRepository;
import org.springframework.stereotype.Service;

@Service
public class OperationalTaskCommandServiceImpl implements OperationalTaskCommandService {
    private final OperationalTaskRepository repository;

    public OperationalTaskCommandServiceImpl(OperationalTaskRepository repository) {
        this.repository = repository;
    }

    public Result<OperationalTask> handle(CreateOperationalTaskCommand command) {
        if (repository.existsById(command.id())) return Result.failure(new ApplicationError("conflict", "Operational task already exists"));
        try {
            OperationalTask task = new OperationalTask(command.id(), command.title(), command.description(), command.priority(), command.status(), command.zoneId(), command.zoneName(), command.alertId(), command.createdAt());
            return Result.success(repository.save(task));
        } catch (IllegalArgumentException exception) {
            return Result.failure(ApplicationError.validation(exception.getMessage()));
        }
    }

    public Result<OperationalTask> handle(UpdateOperationalTaskCommand command) {
        return repository.findById(command.id())
            .map(task -> updateTask(command, task))
            .orElseGet(() -> Result.failure(ApplicationError.notFound("Operational task not found")));
    }

    public Result<OperationalTask> handle(ChangeOperationalTaskStatusCommand command) {
        return repository.findById(command.id())
            .map(task -> changeStatus(command, task))
            .orElseGet(() -> Result.failure(ApplicationError.notFound("Operational task not found")));
    }

    private Result<OperationalTask> updateTask(UpdateOperationalTaskCommand command, OperationalTask task) {
        try {
            task.updateInformation(command.title(), command.description(), command.priority(), command.status(), command.zoneId(), command.zoneName(), command.alertId());
            return Result.success(repository.save(task));
        } catch (IllegalArgumentException exception) {
            return Result.failure(ApplicationError.validation(exception.getMessage()));
        }
    }

    private Result<OperationalTask> changeStatus(ChangeOperationalTaskStatusCommand command, OperationalTask task) {
        try {
            task.changeStatus(command.status());
            return Result.success(repository.save(task));
        } catch (IllegalArgumentException exception) {
            return Result.failure(ApplicationError.validation(exception.getMessage()));
        }
    }
}
