package com.retailpulse.platform.storeoperations.interfaces.rest;

import com.retailpulse.platform.storeoperations.application.commandservices.OperationalTaskCommandService;
import com.retailpulse.platform.storeoperations.application.queryservices.OperationalTaskQueryService;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.*;
import com.retailpulse.platform.storeoperations.interfaces.rest.transform.*;
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
    public OperationalTasksController(OperationalTaskCommandService commandService, OperationalTaskQueryService queryService) { this.commandService = commandService; this.queryService = queryService; }
    @GetMapping @Operation(summary = "Get all operational tasks")
    public List<OperationalTaskResource> all() { return queryService.getAll().stream().map(OperationalTaskResourceFromEntityAssembler::toResource).toList(); }
    @GetMapping("/pending") @Operation(summary = "Get pending operational tasks")
    public List<OperationalTaskResource> pending() { return queryService.getPending().stream().map(OperationalTaskResourceFromEntityAssembler::toResource).toList(); }
    @PostMapping @Operation(summary = "Create operational task")
    public OperationalTaskResource create(@Valid @RequestBody CreateOperationalTaskResource r) { return OperationalTaskResourceFromEntityAssembler.toResource(commandService.handle(CreateOperationalTaskCommandFromResourceAssembler.toCommand(r))); }
    @PatchMapping("/{taskId}/complete") @Operation(summary = "Complete operational task")
    public OperationalTaskResource complete(@PathVariable Long taskId) { return OperationalTaskResourceFromEntityAssembler.toResource(commandService.handle(CompleteOperationalTaskCommandFromResourceAssembler.toCommand(taskId))); }
}
