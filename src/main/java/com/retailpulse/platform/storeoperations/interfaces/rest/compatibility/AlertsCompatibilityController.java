package com.retailpulse.platform.storeoperations.interfaces.rest.compatibility;

import com.retailpulse.platform.storeoperations.application.queryservices.OperationalAlertQueryService;
import com.retailpulse.platform.storeoperations.domain.model.queries.GetAllOperationalAlertsQuery;
import com.retailpulse.platform.storeoperations.domain.model.queries.GetOperationalAlertByIdQuery;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.OperationalAlertResource;
import com.retailpulse.platform.storeoperations.interfaces.rest.transform.OperationalAlertResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/alerts")
@Tag(name = "Store Operations - Alerts Compatibility")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Request completed successfully"),
    @ApiResponse(responseCode = "404", description = "Resource not found")
})
public class AlertsCompatibilityController {
    private final OperationalAlertQueryService queryService;

    public AlertsCompatibilityController(OperationalAlertQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    @Operation(summary = "Get all operational alerts using legacy JSON Server collection name")
    public List<OperationalAlertResource> all() {
        return queryService.handle(new GetAllOperationalAlertsQuery()).stream().map(OperationalAlertResourceFromEntityAssembler::toResource).toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get operational alert by id using legacy JSON Server collection name")
    public ResponseEntity<OperationalAlertResource> byId(@PathVariable String id) {
        return queryService.handle(new GetOperationalAlertByIdQuery(id))
            .map(OperationalAlertResourceFromEntityAssembler::toResource)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
