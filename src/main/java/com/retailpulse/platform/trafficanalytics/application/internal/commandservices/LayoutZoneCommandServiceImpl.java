package com.retailpulse.platform.trafficanalytics.application.internal.commandservices;

import com.retailpulse.platform.shared.application.result.ApplicationError;
import com.retailpulse.platform.shared.application.result.Result;
import com.retailpulse.platform.trafficanalytics.application.commandservices.LayoutZoneCommandService;
import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.LayoutZone;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.CreateLayoutZoneCommand;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.UpdateLayoutZoneCommand;
import com.retailpulse.platform.trafficanalytics.domain.repositories.LayoutZoneRepository;
import org.springframework.stereotype.Service;

@Service
public class LayoutZoneCommandServiceImpl implements LayoutZoneCommandService {
    private final LayoutZoneRepository repository;

    public LayoutZoneCommandServiceImpl(LayoutZoneRepository repository) {
        this.repository = repository;
    }

    public Result<LayoutZone> handle(CreateLayoutZoneCommand command) {
        if (repository.existsById(command.id())) {
            return Result.failure(ApplicationError.validation("Layout zone already exists"));
        }
        try {
            LayoutZone layoutZone = new LayoutZone(command.id(), command.name(), command.x(), command.y(), command.width(), command.height(), command.type());
            return Result.success(repository.save(layoutZone));
        } catch (IllegalArgumentException exception) {
            return Result.failure(ApplicationError.validation(exception.getMessage()));
        }
    }

    public Result<LayoutZone> handle(UpdateLayoutZoneCommand command) {
        return repository.findById(command.id())
            .map(layoutZone -> updateLayoutZone(command, layoutZone))
            .orElseGet(() -> Result.failure(ApplicationError.notFound("Layout zone not found")));
    }

    private Result<LayoutZone> updateLayoutZone(UpdateLayoutZoneCommand command, LayoutZone layoutZone) {
        try {
            layoutZone.update(command.name(), command.x(), command.y(), command.width(), command.height(), command.type());
            return Result.success(repository.save(layoutZone));
        } catch (IllegalArgumentException exception) {
            return Result.failure(ApplicationError.validation(exception.getMessage()));
        }
    }
}
