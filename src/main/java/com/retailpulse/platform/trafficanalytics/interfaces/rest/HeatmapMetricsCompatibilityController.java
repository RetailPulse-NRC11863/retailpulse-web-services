package com.retailpulse.platform.trafficanalytics.interfaces.rest;

import com.retailpulse.platform.trafficanalytics.application.queryservices.HeatmapMetricQueryService;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetAllHeatmapMetricsQuery;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.HeatmapMetricResource;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.transform.HeatmapMetricResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/heatmapMetrics")
@Tag(name = "Traffic Analytics - Heatmap Metrics Compatibility")
@ApiResponses({@ApiResponse(responseCode = "200", description = "Request completed successfully")})
public class HeatmapMetricsCompatibilityController {
    private final HeatmapMetricQueryService queryService;

    public HeatmapMetricsCompatibilityController(HeatmapMetricQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    @Operation(summary = "Get all heatmap metrics using the legacy JSON Server collection name")
    public List<HeatmapMetricResource> getAll() {
        return queryService.handle(new GetAllHeatmapMetricsQuery()).stream().map(HeatmapMetricResourceFromEntityAssembler::toResource).toList();
    }
}
