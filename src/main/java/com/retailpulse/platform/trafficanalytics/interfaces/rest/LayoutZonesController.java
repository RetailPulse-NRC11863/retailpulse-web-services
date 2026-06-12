package com.retailpulse.platform.trafficanalytics.interfaces.rest;

import com.retailpulse.platform.shared.application.result.ApplicationError;
import com.retailpulse.platform.shared.interfaces.rest.resources.ErrorResource;
import com.retailpulse.platform.shared.interfaces.rest.transform.ErrorResponseAssembler;
import com.retailpulse.platform.trafficanalytics.application.commandservices.LayoutZoneCommandService;
import com.retailpulse.platform.trafficanalytics.application.queryservices.LayoutZoneQueryService;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetAllLayoutZonesQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetLayoutZoneByIdQuery;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.CreateLayoutZoneResource;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.LayoutZoneResource;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.UpdateLayoutZoneResource;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.transform.CreateLayoutZoneCommandFromResourceAssembler;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.transform.LayoutZoneResourceFromEntityAssembler;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.transform.UpdateLayoutZoneCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/zones")
@Tag(name = "Traffic Analytics - Layout Zones")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Request completed successfully"),
    @ApiResponse(responseCode = "201", description = "Resource created successfully"),
    @ApiResponse(responseCode = "400", description = "Invalid request body"),
    @ApiResponse(responseCode = "404", description = "Resource not found")
})
public class LayoutZonesController {
    private final LayoutZoneCommandService commandService;
    private final LayoutZoneQueryService queryService;

    public LayoutZonesController(LayoutZoneCommandService commandService, LayoutZoneQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @GetMapping
    @Operation(summary = "Get all layout zones")
    public List<LayoutZoneResource> getAll() {
        return queryService.handle(new GetAllLayoutZonesQuery()).stream().map(LayoutZoneResourceFromEntityAssembler::toResource).toList();
    }

    @GetMapping("/{zoneId}")
    @Operation(summary = "Get layout zone by id")
    public ResponseEntity<LayoutZoneResource> getById(@PathVariable String zoneId) {
        return queryService.handle(new GetLayoutZoneByIdQuery(zoneId))
            .map(LayoutZoneResourceFromEntityAssembler::toResource)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create layout zone")
    public ResponseEntity<?> create(@Valid @RequestBody CreateLayoutZoneResource resource) {
        var result = commandService.handle(CreateLayoutZoneCommandFromResourceAssembler.toCommand(resource));
        if (result.isSuccess()) {
            LayoutZoneResource body = LayoutZoneResourceFromEntityAssembler.toResource(result.value().orElseThrow());
            return ResponseEntity.created(URI.create("/api/v1/zones/" + body.id())).body(body);
        }
        return badRequest(result.error().orElseThrow());
    }

    @PutMapping("/{zoneId}")
    @Operation(summary = "Update layout zone")
    public ResponseEntity<?> update(@PathVariable String zoneId, @Valid @RequestBody UpdateLayoutZoneResource resource) {
        var result = commandService.handle(UpdateLayoutZoneCommandFromResourceAssembler.toCommand(zoneId, resource));
        if (result.isSuccess()) {
            return ResponseEntity.ok(LayoutZoneResourceFromEntityAssembler.toResource(result.value().orElseThrow()));
        }
        ApplicationError error = result.error().orElseThrow();
        if ("not_found".equals(error.code())) return notFound(error);
        return badRequest(error);
    }

    private ResponseEntity<ErrorResource> badRequest(ApplicationError error) {
        return ResponseEntity.badRequest().body(ErrorResponseAssembler.toResource(error.code(), error.message(), List.of()));
    }

    private ResponseEntity<ErrorResource> notFound(ApplicationError error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponseAssembler.toResource(error.code(), error.message(), List.of()));
    }
}
