package com.retailpulse.platform.trafficanalytics.interfaces.rest;

import com.retailpulse.platform.trafficanalytics.application.queryservices.LayoutZoneQueryService;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetAllLayoutZonesQuery;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.LayoutZoneResource;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.transform.LayoutZoneResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/storeLayoutZones")
@Tag(name = "Traffic Analytics - Layout Zones Compatibility")
@ApiResponses({@ApiResponse(responseCode = "200", description = "Request completed successfully")})
public class StoreLayoutZonesCompatibilityController {
    private final LayoutZoneQueryService queryService;

    public StoreLayoutZonesCompatibilityController(LayoutZoneQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    @Operation(summary = "Get all layout zones using the legacy JSON Server collection name")
    public List<LayoutZoneResource> getAll() {
        return queryService.handle(new GetAllLayoutZonesQuery()).stream().map(LayoutZoneResourceFromEntityAssembler::toResource).toList();
    }
}
