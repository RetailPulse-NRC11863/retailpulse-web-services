package com.retailpulse.platform.trafficanalytics.interfaces.rest;

import com.retailpulse.platform.trafficanalytics.application.commandservices.MovementEventCommandService;
import com.retailpulse.platform.trafficanalytics.application.queryservices.TrafficAnalyticsQueryService;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.*;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/traffic")
@Tag(name = "Traffic Analytics")
public class TrafficAnalyticsController {
    private final TrafficAnalyticsQueryService queryService;
    private final MovementEventCommandService commandService;
    public TrafficAnalyticsController(TrafficAnalyticsQueryService queryService, MovementEventCommandService commandService) { this.queryService = queryService; this.commandService = commandService; }
    @GetMapping("/heatmap") @Operation(summary = "Get heatmap metrics")
    public List<LegacyHeatmapMetricResource> heatmap() { return queryService.heatmap().stream().map(LegacyHeatmapMetricResourceFromEntityAssembler::toResource).toList(); }
    @GetMapping("/zones/metrics") @Operation(summary = "Get zone metrics")
    public List<ZoneMetricResource> zones() { return queryService.zoneMetrics().stream().map(ZoneMetricResourceFromEntityAssembler::toResource).toList(); }
    @GetMapping("/congestion") @Operation(summary = "Get congestion metrics")
    public List<CongestionMetricResource> congestion() { return queryService.congestion().stream().map(CongestionMetricResourceFromEntityAssembler::toResource).toList(); }
    @PostMapping("/movement-events") @Operation(summary = "Register movement event")
    public void register(@Valid @RequestBody RegisterMovementEventResource resource) { commandService.handle(RegisterMovementEventCommandFromResourceAssembler.toCommand(resource)); }
}
