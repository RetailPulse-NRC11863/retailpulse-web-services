package com.retailpulse.platform.storeoperations.interfaces.rest;

import com.retailpulse.platform.shared.application.result.ApplicationError;
import com.retailpulse.platform.shared.interfaces.rest.resources.ErrorResource;
import com.retailpulse.platform.shared.interfaces.rest.transform.ErrorResponseAssembler;
import com.retailpulse.platform.storeoperations.application.commandservices.OperationalTaskCommandService;
import com.retailpulse.platform.storeoperations.application.queryservices.OperationalTaskQueryService;
import com.retailpulse.platform.storeoperations.domain.model.queries.GetAllOperationalTasksQuery;
import com.retailpulse.platform.storeoperations.domain.model.queries.GetOperationalTaskByIdQuery;
import com.retailpulse.platform.storeoperations.domain.model.queries.GetPendingOperationalTasksQuery;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.ChangeOperationalTaskStatusResource;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.CreateOperationalTaskResource;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.OperationalTaskResource;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.UpdateOperationalTaskResource;
import com.retailpulse.platform.storeoperations.interfaces.rest.transform.ChangeOperationalTaskStatusCommandFromResourceAssembler;
import com.retailpulse.platform.storeoperations.interfaces.rest.transform.CreateOperationalTaskCommandFromResourceAssembler;
import com.retailpulse.platform.storeoperations.interfaces.rest.transform.OperationalTaskResourceFromEntityAssembler;
import com.retailpulse.platform.storeoperations.interfaces.rest.transform.UpdateOperationalTaskCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/operational-tasks")
@Tag(name = "Store Operations - Operational Tasks")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Request completed successfully"),
    @ApiResponse(responseCode = "201", description = "Resource created successfully"),
    @ApiResponse(responseCode = "400", description = "Invalid request body"),
    @ApiResponse(responseCode = "404", description = "Resource not found"),
    @ApiResponse(responseCode = "409", description = "Resource conflict")
})
public class OperationalTasksController {
    private final OperationalTaskCommandService commandService;
    private final OperationalTaskQueryService queryService;

    public OperationalTasksController(OperationalTaskCommandService commandService, OperationalTaskQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @GetMapping
    @Operation(summary = "Get all operational tasks")
    public List<OperationalTaskResource> all() {
        return queryService.handle(new GetAllOperationalTasksQuery()).stream().map(OperationalTaskResourceFromEntityAssembler::toResource).toList();
    }

    @GetMapping("/pending")
    @Operation(summary = "Get pending operational tasks")
    public List<OperationalTaskResource> pending() {
        return queryService.handle(new GetPendingOperationalTasksQuery()).stream().map(OperationalTaskResourceFromEntityAssembler::toResource).toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get operational task by id")
    public ResponseEntity<OperationalTaskResource> byId(@PathVariable String id) {
        return queryService.handle(new GetOperationalTaskByIdQuery(id))
            .map(OperationalTaskResourceFromEntityAssembler::toResource)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create operational task")
    public ResponseEntity<?> create(@Valid @RequestBody CreateOperationalTaskResource resource) {
        var result = commandService.handle(CreateOperationalTaskCommandFromResourceAssembler.toCommand(resource));
        if (result.isSuccess()) {
            OperationalTaskResource body = OperationalTaskResourceFromEntityAssembler.toResource(result.value().orElseThrow());
            return ResponseEntity.created(URI.create("/api/v1/operational-tasks/" + body.id())).body(body);
        }
        return mapError(result.error().orElseThrow());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update operational task")
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody UpdateOperationalTaskResource resource) {
        var result = commandService.handle(UpdateOperationalTaskCommandFromResourceAssembler.toCommand(id, resource));
        if (result.isSuccess()) return ResponseEntity.ok(OperationalTaskResourceFromEntityAssembler.toResource(result.value().orElseThrow()));
        return mapError(result.error().orElseThrow());
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Change operational task status")
    public ResponseEntity<?> changeStatus(@PathVariable String id, @Valid @RequestBody ChangeOperationalTaskStatusResource resource) {
        var result = commandService.handle(ChangeOperationalTaskStatusCommandFromResourceAssembler.toCommand(id, resource));
        if (result.isSuccess()) return ResponseEntity.ok(OperationalTaskResourceFromEntityAssembler.toResource(result.value().orElseThrow()));
        return mapError(result.error().orElseThrow());
    }

    private ResponseEntity<ErrorResource> mapError(ApplicationError error) {
        if ("not_found".equals(error.code())) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponseAssembler.toResource(error.code(), error.message(), List.of()));
        if ("conflict".equals(error.code())) return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponseAssembler.toResource(error.code(), error.message(), List.of()));
        return ResponseEntity.badRequest().body(ErrorResponseAssembler.toResource(error.code(), error.message(), List.of()));
    }
}
