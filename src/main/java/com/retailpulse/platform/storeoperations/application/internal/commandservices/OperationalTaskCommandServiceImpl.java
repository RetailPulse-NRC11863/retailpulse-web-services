package com.retailpulse.platform.storeoperations.application.internal.commandservices;

import com.retailpulse.platform.storeoperations.application.commandservices.OperationalTaskCommandService;
import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalTask;
import com.retailpulse.platform.storeoperations.domain.model.commands.CompleteOperationalTaskCommand;
import com.retailpulse.platform.storeoperations.domain.model.commands.CreateOperationalTaskCommand;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.TaskStatus;
import com.retailpulse.platform.storeoperations.domain.repositories.OperationalTaskRepository;
import org.springframework.stereotype.Service;

@Service
public class OperationalTaskCommandServiceImpl implements OperationalTaskCommandService {
    private final OperationalTaskRepository repository;
    public OperationalTaskCommandServiceImpl(OperationalTaskRepository repository) { this.repository = repository; }
    public OperationalTask handle(CreateOperationalTaskCommand c) { return repository.save(new OperationalTask(null, c.storeId(), c.title(), c.description(), TaskStatus.PENDING, c.priority())); }
    public OperationalTask handle(CompleteOperationalTaskCommand c) { OperationalTask t = repository.findById(c.taskId()).orElseThrow(() -> new IllegalArgumentException("Task not found")); t.complete(); return repository.save(t); }
}
