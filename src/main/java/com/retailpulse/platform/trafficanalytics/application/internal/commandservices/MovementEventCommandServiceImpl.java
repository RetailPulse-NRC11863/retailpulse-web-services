package com.retailpulse.platform.trafficanalytics.application.internal.commandservices;

import com.retailpulse.platform.trafficanalytics.application.commandservices.MovementEventCommandService;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.RegisterMovementEventCommand;
import com.retailpulse.platform.trafficanalytics.domain.model.entities.MovementEvent;
import com.retailpulse.platform.trafficanalytics.domain.repositories.MovementEventRepository;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;

@Service
public class MovementEventCommandServiceImpl implements MovementEventCommandService {
    private final MovementEventRepository repository;
    public MovementEventCommandServiceImpl(MovementEventRepository repository) { this.repository = repository; }
    public MovementEvent handle(RegisterMovementEventCommand command) { return repository.save(new MovementEvent(null, command.zoneId(), command.eventType(), command.occurredAt() == null ? OffsetDateTime.now() : command.occurredAt())); }
}
