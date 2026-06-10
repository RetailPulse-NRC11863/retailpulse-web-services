package com.retailpulse.platform.assistedshopping.application.internal.commandservices;

import com.retailpulse.platform.assistedshopping.application.commandservices.KioskSessionCommandService;
import com.retailpulse.platform.assistedshopping.domain.model.aggregates.KioskSession;
import com.retailpulse.platform.assistedshopping.domain.model.commands.RegisterProductSearchCommand;
import com.retailpulse.platform.assistedshopping.domain.model.commands.StartKioskSessionCommand;
import com.retailpulse.platform.assistedshopping.domain.model.entities.ProductSearch;
import com.retailpulse.platform.assistedshopping.domain.model.valueobjects.SearchResultStatus;
import com.retailpulse.platform.assistedshopping.domain.model.valueobjects.SessionStatus;
import com.retailpulse.platform.assistedshopping.domain.repositories.KioskSessionRepository;
import org.springframework.stereotype.Service;

@Service
public class KioskSessionCommandServiceImpl implements KioskSessionCommandService {
    private final KioskSessionRepository repository;
    public KioskSessionCommandServiceImpl(KioskSessionRepository repository) { this.repository = repository; }
    public KioskSession handle(StartKioskSessionCommand command) { return repository.save(new KioskSession(null, command.storeId(), SessionStatus.ACTIVE)); }
    public ProductSearch handle(RegisterProductSearchCommand command) { return new ProductSearch(null, command.sessionId(), command.query(), command.productId(), command.productId() == null ? SearchResultStatus.NOT_FOUND : SearchResultStatus.FOUND); }
}
