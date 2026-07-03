package com.retailpulse.platform.storefoundation.application.internal.commandservices;

import com.retailpulse.platform.storefoundation.application.commandservices.ZoneCommandService;
import com.retailpulse.platform.storefoundation.domain.model.commands.CreateZoneCommand;
import com.retailpulse.platform.storefoundation.domain.model.commands.UpdateZoneCommand;
import com.retailpulse.platform.storefoundation.domain.model.entities.Zone;
import com.retailpulse.platform.storefoundation.domain.repositories.ZoneRepository;
import org.springframework.stereotype.Service;

@Service
public class ZoneCommandServiceImpl implements ZoneCommandService {
    private final ZoneRepository repository;
    public ZoneCommandServiceImpl(ZoneRepository repository) { this.repository = repository; }
    public Zone handle(CreateZoneCommand command) { return repository.save(new Zone(null, command.storeId(), command.name(), command.type(), command.capacity(), command.x(), command.y(), command.width(), command.height())); }
    public Zone handle(UpdateZoneCommand command) {
        Zone zone = repository.findById(command.zoneId()).orElseThrow(() -> new IllegalArgumentException("Zone not found"));
        zone.update(command.name(), command.type(), command.capacity(), command.x(), command.y(), command.width(), command.height());
        return repository.save(zone);
    }
    public void delete(Long zoneId) { repository.deleteById(zoneId); }
}
