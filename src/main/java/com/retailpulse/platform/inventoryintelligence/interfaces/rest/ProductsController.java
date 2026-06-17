package com.retailpulse.platform.inventoryintelligence.interfaces.rest;

import com.retailpulse.platform.inventoryintelligence.application.commandservices.ProductCommandService;
import com.retailpulse.platform.inventoryintelligence.application.queryservices.ProductQueryService;
import com.retailpulse.platform.inventoryintelligence.domain.model.commands.DeleteProductCommand;
import com.retailpulse.platform.inventoryintelligence.domain.model.queries.GetAllProductsQuery;
import com.retailpulse.platform.inventoryintelligence.domain.model.queries.GetProductByIdQuery;
import com.retailpulse.platform.inventoryintelligence.domain.model.queries.SearchProductsQuery;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.resources.CreateProductResource;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.resources.ProductResource;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.resources.UpdateProductResource;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.transform.CreateProductCommandFromResourceAssembler;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.transform.ProductResourceFromEntityAssembler;
import com.retailpulse.platform.inventoryintelligence.interfaces.rest.transform.UpdateProductCommandFromResourceAssembler;
import com.retailpulse.platform.shared.application.result.ApplicationError;
import com.retailpulse.platform.shared.interfaces.rest.resources.ErrorResource;
import com.retailpulse.platform.shared.interfaces.rest.transform.ErrorResponseAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Inventory Intelligence - Products")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Request completed successfully"),
    @ApiResponse(responseCode = "201", description = "Resource created successfully"),
    @ApiResponse(responseCode = "204", description = "Resource deleted successfully"),
    @ApiResponse(responseCode = "400", description = "Invalid request body"),
    @ApiResponse(responseCode = "404", description = "Resource not found"),
    @ApiResponse(responseCode = "409", description = "Resource conflict")
})
public class ProductsController {
    private final ProductCommandService commandService;
    private final ProductQueryService queryService;

    public ProductsController(ProductCommandService commandService, ProductQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @GetMapping
    @Operation(summary = "Get all products")
    public List<ProductResource> getAll() {
        return queryService.handle(new GetAllProductsQuery()).stream().map(ProductResourceFromEntityAssembler::toResource).toList();
    }

    @GetMapping("/search")
    @Operation(summary = "Search products by name, category or zone name")
    public List<ProductResource> search(@RequestParam(defaultValue = "") String query) {
        return queryService.handle(new SearchProductsQuery(query)).stream().map(ProductResourceFromEntityAssembler::toResource).toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by id")
    public ResponseEntity<ProductResource> getById(@PathVariable String id) {
        return queryService.handle(new GetProductByIdQuery(id))
            .map(ProductResourceFromEntityAssembler::toResource)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create product")
    public ResponseEntity<?> create(@Valid @RequestBody CreateProductResource resource) {
        var result = commandService.handle(CreateProductCommandFromResourceAssembler.toCommand(resource));
        if (result.isSuccess()) {
            ProductResource body = ProductResourceFromEntityAssembler.toResource(result.value().orElseThrow());
            return ResponseEntity.created(URI.create("/api/v1/products/" + body.id())).body(body);
        }
        ApplicationError error = result.error().orElseThrow();
        if ("conflict".equals(error.code())) return conflict(error);
        return badRequest(error);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update product")
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody UpdateProductResource resource) {
        var result = commandService.handle(UpdateProductCommandFromResourceAssembler.toCommand(id, resource));
        if (result.isSuccess()) {
            return ResponseEntity.ok(ProductResourceFromEntityAssembler.toResource(result.value().orElseThrow()));
        }
        ApplicationError error = result.error().orElseThrow();
        if ("not_found".equals(error.code())) return notFound(error);
        return badRequest(error);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product")
    public ResponseEntity<?> delete(@PathVariable String id) {
        var result = commandService.handle(new DeleteProductCommand(id));
        if (result.isSuccess()) return ResponseEntity.noContent().build();
        return notFound(result.error().orElseThrow());
    }

    private ResponseEntity<ErrorResource> badRequest(ApplicationError error) {
        return ResponseEntity.badRequest().body(ErrorResponseAssembler.toResource(error.code(), error.message(), List.of()));
    }

    private ResponseEntity<ErrorResource> notFound(ApplicationError error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponseAssembler.toResource(error.code(), error.message(), List.of()));
    }

    private ResponseEntity<ErrorResource> conflict(ApplicationError error) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponseAssembler.toResource(error.code(), error.message(), List.of()));
    }
}
