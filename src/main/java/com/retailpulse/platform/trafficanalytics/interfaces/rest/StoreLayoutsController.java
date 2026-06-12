package com.retailpulse.platform.trafficanalytics.interfaces.rest;

import com.retailpulse.platform.shared.application.result.ApplicationError;
import com.retailpulse.platform.shared.interfaces.rest.resources.ErrorResource;
import com.retailpulse.platform.shared.interfaces.rest.transform.ErrorResponseAssembler;
import com.retailpulse.platform.trafficanalytics.application.commandservices.StoreLayoutCommandService;
import com.retailpulse.platform.trafficanalytics.application.queryservices.LayoutZoneQueryService;
import com.retailpulse.platform.trafficanalytics.application.queryservices.StoreLayoutQueryService;
import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.LayoutZone;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetAllLayoutZonesQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetAllStoreLayoutsQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetCurrentStoreLayoutQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetStoreLayoutByIdQuery;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.CreateStoreLayoutResource;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.StoreLayoutResource;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.UpdateStoreLayoutResource;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.transform.CreateStoreLayoutCommandFromResourceAssembler;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.transform.StoreLayoutResourceFromEntityAssembler;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.transform.UpdateStoreLayoutCommandFromResourceAssembler;
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
@RequestMapping("/api/v1/layouts")
@Tag(name = "Traffic Analytics - Store Layouts")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Request completed successfully"),
    @ApiResponse(responseCode = "201", description = "Resource created successfully"),
    @ApiResponse(responseCode = "400", description = "Invalid request body"),
    @ApiResponse(responseCode = "404", description = "Resource not found")
})
public class StoreLayoutsController {
    private final StoreLayoutCommandService commandService;
    private final StoreLayoutQueryService queryService;
    private final LayoutZoneQueryService layoutZoneQueryService;

    public StoreLayoutsController(StoreLayoutCommandService commandService, StoreLayoutQueryService queryService, LayoutZoneQueryService layoutZoneQueryService) {
        this.commandService = commandService;
        this.queryService = queryService;
        this.layoutZoneQueryService = layoutZoneQueryService;
    }

    @GetMapping
    @Operation(summary = "Get all store layouts")
    public List<StoreLayoutResource> getAll() {
        List<LayoutZone> zones = layoutZones();
        return queryService.handle(new GetAllStoreLayoutsQuery()).stream().map(layout -> StoreLayoutResourceFromEntityAssembler.toResource(layout, zones)).toList();
    }

    @GetMapping("/current")
    @Operation(summary = "Get current active store layout")
    public ResponseEntity<StoreLayoutResource> getCurrent() {
        List<LayoutZone> zones = layoutZones();
        return queryService.handle(new GetCurrentStoreLayoutQuery())
            .map(layout -> StoreLayoutResourceFromEntityAssembler.toResource(layout, zones))
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{layoutId}")
    @Operation(summary = "Get store layout by id")
    public ResponseEntity<StoreLayoutResource> getById(@PathVariable String layoutId) {
        List<LayoutZone> zones = layoutZones();
        return queryService.handle(new GetStoreLayoutByIdQuery(layoutId))
            .map(layout -> StoreLayoutResourceFromEntityAssembler.toResource(layout, zones))
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create store layout")
    public ResponseEntity<?> create(@Valid @RequestBody CreateStoreLayoutResource resource) {
        var result = commandService.handle(CreateStoreLayoutCommandFromResourceAssembler.toCommand(resource));
        if (result.isSuccess()) {
            StoreLayoutResource body = StoreLayoutResourceFromEntityAssembler.toResource(result.value().orElseThrow(), layoutZones());
            return ResponseEntity.created(URI.create("/api/v1/layouts/" + body.id())).body(body);
        }
        return badRequest(result.error().orElseThrow());
    }

    @PutMapping("/{layoutId}")
    @Operation(summary = "Update store layout")
    public ResponseEntity<?> update(@PathVariable String layoutId, @Valid @RequestBody UpdateStoreLayoutResource resource) {
        var result = commandService.handle(UpdateStoreLayoutCommandFromResourceAssembler.toCommand(layoutId, resource));
        if (result.isSuccess()) {
            return ResponseEntity.ok(StoreLayoutResourceFromEntityAssembler.toResource(result.value().orElseThrow(), layoutZones()));
        }
        ApplicationError error = result.error().orElseThrow();
        if ("not_found".equals(error.code())) return notFound(error);
        return badRequest(error);
    }

    private List<LayoutZone> layoutZones() {
        return layoutZoneQueryService.handle(new GetAllLayoutZonesQuery());
    }

    private ResponseEntity<ErrorResource> badRequest(ApplicationError error) {
        return ResponseEntity.badRequest().body(ErrorResponseAssembler.toResource(error.code(), error.message(), List.of()));
    }

    private ResponseEntity<ErrorResource> notFound(ApplicationError error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponseAssembler.toResource(error.code(), error.message(), List.of()));
    }
}
