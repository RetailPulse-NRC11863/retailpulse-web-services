package com.retailpulse.platform.storeoperations.application.internal.commandservices;

import com.retailpulse.platform.storeoperations.application.commandservices.OperationalAlertCommandService;
import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalAlert;
import com.retailpulse.platform.storeoperations.domain.model.commands.CreateOperationalAlertCommand;
import com.retailpulse.platform.storeoperations.domain.model.commands.ResolveOperationalAlertCommand;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertStatus;
import com.retailpulse.platform.storeoperations.domain.repositories.OperationalAlertRepository;
import org.springframework.stereotype.Service;

@Service
public class OperationalAlertCommandServiceImpl implements OperationalAlertCommandService {
    private final OperationalAlertRepository repository;
    public OperationalAlertCommandServiceImpl(OperationalAlertRepository repository) { this.repository = repository; }
    public OperationalAlert handle(CreateOperationalAlertCommand c) { return repository.save(new OperationalAlert(null, c.storeId(), c.title(), c.description(), c.type(), AlertStatus.ACTIVE, c.priority(), c.productId(), c.zoneId(), c.source(), c.triggerReason())); }
    public OperationalAlert handle(ResolveOperationalAlertCommand c) { OperationalAlert a = repository.findById(c.alertId()).orElseThrow(() -> new IllegalArgumentException("Alert not found")); a.resolve(); return repository.save(a); }
}
