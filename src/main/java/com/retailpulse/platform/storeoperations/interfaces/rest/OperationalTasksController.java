package com.retailpulse.platform.storeoperations.interfaces.rest;

import com.retailpulse.platform.storeoperations.application.commandservices.OperationalTaskCommandService;
import com.retailpulse.platform.storeoperations.application.queryservices.OperationalTaskQueryService;
import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalTask;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.*;
import com.retailpulse.platform.storeoperations.interfaces.rest.transform.*;
import com.retailpulse.platform.subscription.domain.repositories.SaasAccountRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/operational-tasks")
@Tag(name = "Operational Tasks")
public class OperationalTasksController {
    private final OperationalTaskCommandService commandService;
    private final OperationalTaskQueryService queryService;
    private final SaasAccountRepository accountRepository;
    public OperationalTasksController(OperationalTaskCommandService commandService, OperationalTaskQueryService queryService, SaasAccountRepository accountRepository) { this.commandService = commandService; this.queryService = queryService; this.accountRepository = accountRepository; }
    @GetMapping @Operation(summary = "Get all operational tasks")
    public List<OperationalTaskResource> all(@RequestHeader(value = "X-Owner-Email", required = false) String ownerEmail) { return queryService.getAll().stream().filter(task -> belongsToCurrentStore(task, ownerEmail)).map(OperationalTaskResourceFromEntityAssembler::toResource).toList(); }
    @GetMapping("/pending") @Operation(summary = "Get pending operational tasks")
    public List<OperationalTaskResource> pending(@RequestHeader(value = "X-Owner-Email", required = false) String ownerEmail) { return queryService.getPending().stream().filter(task -> belongsToCurrentStore(task, ownerEmail)).map(OperationalTaskResourceFromEntityAssembler::toResource).toList(); }
    @PostMapping @Operation(summary = "Create operational task")
    public OperationalTaskResource create(@Valid @RequestBody CreateOperationalTaskResource r) { return OperationalTaskResourceFromEntityAssembler.toResource(commandService.handle(CreateOperationalTaskCommandFromResourceAssembler.toCommand(r))); }
    @PatchMapping("/{taskId}/complete") @Operation(summary = "Complete operational task")
    public OperationalTaskResource complete(@PathVariable Long taskId) { return OperationalTaskResourceFromEntityAssembler.toResource(commandService.handle(CompleteOperationalTaskCommandFromResourceAssembler.toCommand(taskId))); }

    private boolean belongsToCurrentStore(OperationalTask task, String ownerEmail) {
        Long storeId = currentStoreId(ownerEmail);
        boolean traceable = task.getProductId() != null || task.getZoneId() != null || task.getSource() != null;
        return traceable && (storeId == null || storeId.equals(task.getStoreId()));
    }

    private Long currentStoreId(String ownerEmail) {
        if (ownerEmail != null && !ownerEmail.isBlank()) {
            return accountRepository.findByOwnerEmail(ownerEmail).map(account -> account.getStoreId()).orElse(null);
        }
        return accountRepository.findFirst().map(account -> account.getStoreId()).orElse(null);
    }
}
