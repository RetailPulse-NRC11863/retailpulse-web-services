package com.retailpulse.platform.storeoperations.interfaces.rest.compatibility;

import com.retailpulse.platform.storeoperations.application.queryservices.OperationalTaskQueryService;
import com.retailpulse.platform.storeoperations.domain.model.queries.GetAllOperationalTasksQuery;
import com.retailpulse.platform.storeoperations.domain.model.queries.GetOperationalTaskByIdQuery;
import com.retailpulse.platform.storeoperations.interfaces.rest.resources.OperationalTaskResource;
import com.retailpulse.platform.storeoperations.interfaces.rest.transform.OperationalTaskResourceFromEntityAssembler;
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
@RequestMapping("/api/v1/operationalTasks")
@Tag(name = "Store Operations - Operational Tasks Compatibility")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Request completed successfully"),
    @ApiResponse(responseCode = "404", description = "Resource not found")
})
public class OperationalTasksCompatibilityController {
    private final OperationalTaskQueryService queryService;

    public OperationalTasksCompatibilityController(OperationalTaskQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    @Operation(summary = "Get all operational tasks using legacy JSON Server collection name")
    public List<OperationalTaskResource> all() {
        return queryService.handle(new GetAllOperationalTasksQuery()).stream().map(OperationalTaskResourceFromEntityAssembler::toResource).toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get operational task by id using legacy JSON Server collection name")
    public ResponseEntity<OperationalTaskResource> byId(@PathVariable String id) {
        return queryService.handle(new GetOperationalTaskByIdQuery(id))
            .map(OperationalTaskResourceFromEntityAssembler::toResource)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
