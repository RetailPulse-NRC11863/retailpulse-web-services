package com.retailpulse.platform.storeoperations.interfaces.rest;

import com.retailpulse.platform.shared.application.result.ApplicationError;
import com.retailpulse.platform.shared.interfaces.rest.resources.ErrorResource;
import com.retailpulse.platform.shared.interfaces.rest.transform.ErrorResponseAssembler;
import com.retailpulse.platform.storeoperations.application.commandservices.OperationalAlertCommandService;
import com.retailpulse.platform.storeoperations.application.queryservices.OperationalAlertQueryService;
import com.retailpulse.platform.storeoperations.domain.model.commands.ChangeOperationalAlertStatusCommand;
import com.retailpulse.platform.storeoperations.domain.model.queries.GetAllOperationalAlertsQuery;
import com.retailpulse.platform.storeoperations.domain.model.queries.GetOperationalAlertByIdQuery;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.ChangeOperationalAlertStatusResource;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.CreateOperationalAlertResource;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.OperationalAlertResource;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.UpdateOperationalAlertResource;
import com.retailpulse.platform.storeoperations.interfaces.rest.transform.CreateOperationalAlertCommandFromResourceAssembler;
import com.retailpulse.platform.storeoperations.interfaces.rest.transform.OperationalAlertResourceFromEntityAssembler;
import com.retailpulse.platform.storeoperations.interfaces.rest.transform.UpdateOperationalAlertCommandFromResourceAssembler;
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
@RequestMapping("/api/v1/operational-alerts")
@Tag(name = "Store Operations - Operational Alerts")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Request completed successfully"),
    @ApiResponse(responseCode = "201", description = "Resource created successfully"),
    @ApiResponse(responseCode = "400", description = "Invalid request body"),
    @ApiResponse(responseCode = "404", description = "Resource not found"),
    @ApiResponse(responseCode = "409", description = "Resource conflict")
})
public class OperationalAlertsController {
    private final OperationalAlertCommandService commandService;
    private final OperationalAlertQueryService queryService;

    public OperationalAlertsController(OperationalAlertCommandService commandService, OperationalAlertQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @GetMapping
    @Operation(summary = "Get all operational alerts")
    public List<OperationalAlertResource> all() {
        return queryService.handle(new GetAllOperationalAlertsQuery()).stream().map(OperationalAlertResourceFromEntityAssembler::toResource).toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get operational alert by id")
    public ResponseEntity<OperationalAlertResource> byId(@PathVariable String id) {
        return queryService.handle(new GetOperationalAlertByIdQuery(id))
            .map(OperationalAlertResourceFromEntityAssembler::toResource)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create operational alert")
    public ResponseEntity<?> create(@Valid @RequestBody CreateOperationalAlertResource resource) {
        var result = commandService.handle(CreateOperationalAlertCommandFromResourceAssembler.toCommand(resource));
        if (result.isSuccess()) {
            OperationalAlertResource body = OperationalAlertResourceFromEntityAssembler.toResource(result.value().orElseThrow());
            return ResponseEntity.created(URI.create("/api/v1/operational-alerts/" + body.id())).body(body);
        }
        return mapError(result.error().orElseThrow());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update operational alert")
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody UpdateOperationalAlertResource resource) {
        var result = commandService.handle(UpdateOperationalAlertCommandFromResourceAssembler.toCommand(id, resource));
        if (result.isSuccess()) return ResponseEntity.ok(OperationalAlertResourceFromEntityAssembler.toResource(result.value().orElseThrow()));
        return mapError(result.error().orElseThrow());
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Change operational alert status")
    public ResponseEntity<?> changeStatus(@PathVariable String id, @Valid @RequestBody ChangeOperationalAlertStatusResource resource) {
        var result = commandService.handle(new ChangeOperationalAlertStatusCommand(id, resource.status()));
        if (result.isSuccess()) return ResponseEntity.ok(OperationalAlertResourceFromEntityAssembler.toResource(result.value().orElseThrow()));
        return mapError(result.error().orElseThrow());
    }

    private ResponseEntity<ErrorResource> mapError(ApplicationError error) {
        if ("not_found".equals(error.code())) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponseAssembler.toResource(error.code(), error.message(), List.of()));
        if ("conflict".equals(error.code())) return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponseAssembler.toResource(error.code(), error.message(), List.of()));
        return ResponseEntity.badRequest().body(ErrorResponseAssembler.toResource(error.code(), error.message(), List.of()));
    }
}
