package com.retailpulse.platform.trafficanalytics.interfaces.rest;

import com.retailpulse.platform.shared.application.result.ApplicationError;
import com.retailpulse.platform.shared.interfaces.rest.resources.ErrorResource;
import com.retailpulse.platform.shared.interfaces.rest.transform.ErrorResponseAssembler;
import com.retailpulse.platform.trafficanalytics.application.commandservices.HeatmapMetricCommandService;
import com.retailpulse.platform.trafficanalytics.application.queryservices.HeatmapMetricQueryService;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetAllHeatmapMetricsQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetHeatmapMetricByIdQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetHeatmapMetricsByZoneIdQuery;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.CreateHeatmapMetricResource;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.HeatmapMetricResource;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.UpdateHeatmapMetricResource;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.transform.CreateHeatmapMetricCommandFromResourceAssembler;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.transform.HeatmapMetricResourceFromEntityAssembler;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.transform.UpdateHeatmapMetricCommandFromResourceAssembler;
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
@RequestMapping("/api/v1/heatmap-metrics")
@Tag(name = "Traffic Analytics - Heatmap Metrics")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Request completed successfully"),
    @ApiResponse(responseCode = "201", description = "Resource created successfully"),
    @ApiResponse(responseCode = "400", description = "Invalid request body"),
    @ApiResponse(responseCode = "404", description = "Resource not found")
})
public class HeatmapMetricsController {
    private final HeatmapMetricCommandService commandService;
    private final HeatmapMetricQueryService queryService;

    public HeatmapMetricsController(HeatmapMetricCommandService commandService, HeatmapMetricQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @GetMapping
    @Operation(summary = "Get all heatmap metrics")
    public List<HeatmapMetricResource> getAll() {
        return queryService.handle(new GetAllHeatmapMetricsQuery()).stream().map(HeatmapMetricResourceFromEntityAssembler::toResource).toList();
    }

    @GetMapping("/{metricId}")
    @Operation(summary = "Get heatmap metric by id")
    public ResponseEntity<HeatmapMetricResource> getById(@PathVariable String metricId) {
        return queryService.handle(new GetHeatmapMetricByIdQuery(metricId))
            .map(HeatmapMetricResourceFromEntityAssembler::toResource)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-zone/{zoneId}")
    @Operation(summary = "Get heatmap metrics by zone id")
    public List<HeatmapMetricResource> getByZoneId(@PathVariable String zoneId) {
        return queryService.handle(new GetHeatmapMetricsByZoneIdQuery(zoneId)).stream().map(HeatmapMetricResourceFromEntityAssembler::toResource).toList();
    }

    @PostMapping
    @Operation(summary = "Create heatmap metric")
    public ResponseEntity<?> create(@Valid @RequestBody CreateHeatmapMetricResource resource) {
        var result = commandService.handle(CreateHeatmapMetricCommandFromResourceAssembler.toCommand(resource));
        if (result.isSuccess()) {
            HeatmapMetricResource body = HeatmapMetricResourceFromEntityAssembler.toResource(result.value().orElseThrow());
            return ResponseEntity.created(URI.create("/api/v1/heatmap-metrics/" + body.id())).body(body);
        }
        return badRequest(result.error().orElseThrow());
    }

    @PutMapping("/{metricId}")
    @Operation(summary = "Update heatmap metric")
    public ResponseEntity<?> update(@PathVariable String metricId, @Valid @RequestBody UpdateHeatmapMetricResource resource) {
        var result = commandService.handle(UpdateHeatmapMetricCommandFromResourceAssembler.toCommand(metricId, resource));
        if (result.isSuccess()) {
            return ResponseEntity.ok(HeatmapMetricResourceFromEntityAssembler.toResource(result.value().orElseThrow()));
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
