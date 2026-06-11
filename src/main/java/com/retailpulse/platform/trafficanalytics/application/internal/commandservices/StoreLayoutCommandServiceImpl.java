package com.retailpulse.platform.trafficanalytics.application.internal.commandservices;

import com.retailpulse.platform.shared.application.result.ApplicationError;
import com.retailpulse.platform.shared.application.result.Result;
import com.retailpulse.platform.trafficanalytics.application.commandservices.StoreLayoutCommandService;
import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.StoreLayout;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.CreateStoreLayoutCommand;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.UpdateStoreLayoutCommand;
import com.retailpulse.platform.trafficanalytics.domain.repositories.StoreLayoutRepository;
import org.springframework.stereotype.Service;

@Service
public class StoreLayoutCommandServiceImpl implements StoreLayoutCommandService {
    private final StoreLayoutRepository repository;

    public StoreLayoutCommandServiceImpl(StoreLayoutRepository repository) {
        this.repository = repository;
    }

    public Result<StoreLayout> handle(CreateStoreLayoutCommand command) {
        if (repository.existsById(command.id())) {
            return Result.failure(ApplicationError.validation("Store layout already exists"));
        }
        try {
            StoreLayout storeLayout = new StoreLayout(command.id(), command.name(), command.storeName(), command.width(), command.height(), command.active());
            return Result.success(repository.save(storeLayout));
        } catch (IllegalArgumentException exception) {
            return Result.failure(ApplicationError.validation(exception.getMessage()));
        }
    }

    public Result<StoreLayout> handle(UpdateStoreLayoutCommand command) {
        return repository.findById(command.id())
            .map(storeLayout -> updateStoreLayout(command, storeLayout))
            .orElseGet(() -> Result.failure(ApplicationError.notFound("Store layout not found")));
    }

    private Result<StoreLayout> updateStoreLayout(UpdateStoreLayoutCommand command, StoreLayout storeLayout) {
        try {
            storeLayout.update(command.name(), command.storeName(), command.width(), command.height(), command.active());
            return Result.success(repository.save(storeLayout));
        } catch (IllegalArgumentException exception) {
            return Result.failure(ApplicationError.validation(exception.getMessage()));
        }
    }
}
