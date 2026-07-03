package com.retailpulse.platform.storefoundation.interfaces.rest;

import com.retailpulse.platform.storefoundation.application.commandservices.ProductCommandService;
import com.retailpulse.platform.storefoundation.application.queryservices.ProductQueryService;
import com.retailpulse.platform.storefoundation.interfaces.rest.resources.CreateProductResource;
import com.retailpulse.platform.storefoundation.interfaces.rest.resources.ProductResource;
import com.retailpulse.platform.storefoundation.interfaces.rest.resources.UpdateProductResource;
import com.retailpulse.platform.storefoundation.interfaces.rest.transform.CreateProductCommandFromResourceAssembler;
import com.retailpulse.platform.storefoundation.interfaces.rest.transform.ProductResourceFromEntityAssembler;
import com.retailpulse.platform.storefoundation.interfaces.rest.transform.UpdateProductCommandFromResourceAssembler;
import com.retailpulse.platform.subscription.domain.repositories.SaasAccountRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Products")
public class ProductsController {
    private final ProductCommandService commandService;
    private final ProductQueryService queryService;
    private final SaasAccountRepository accountRepository;
    public ProductsController(ProductCommandService commandService, ProductQueryService queryService, SaasAccountRepository accountRepository) { this.commandService = commandService; this.queryService = queryService; this.accountRepository = accountRepository; }
    @GetMapping @Operation(summary = "Get all products")
    public List<ProductResource> getAll(@RequestHeader(value = "X-Owner-Email", required = false) String ownerEmail) {
        Long storeId = currentStoreId(ownerEmail);
        return queryService.handleGetAll().stream()
            .filter(product -> storeId == null || storeId.equals(product.getStoreId()))
            .map(ProductResourceFromEntityAssembler::toResource).toList();
    }
    @GetMapping("/{productId}") @Operation(summary = "Get product by id")
    public ResponseEntity<ProductResource> getById(@PathVariable Long productId) { return queryService.handleGetById(productId).map(ProductResourceFromEntityAssembler::toResource).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @GetMapping("/search") @Operation(summary = "Search products")
    public List<ProductResource> search(@RequestParam String query, @RequestHeader(value = "X-Owner-Email", required = false) String ownerEmail) {
        Long storeId = currentStoreId(ownerEmail);
        return queryService.handleSearch(query).stream()
            .filter(product -> storeId == null || storeId.equals(product.getStoreId()))
            .map(ProductResourceFromEntityAssembler::toResource).toList();
    }
    @PostMapping @Operation(summary = "Create product")
    public ResponseEntity<ProductResource> create(@Valid @RequestBody CreateProductResource resource) { return ResponseEntity.ok(ProductResourceFromEntityAssembler.toResource(commandService.handle(CreateProductCommandFromResourceAssembler.toCommand(resource)))); }
    @PutMapping("/{productId}") @Operation(summary = "Update product")
    public ProductResource update(@PathVariable Long productId, @Valid @RequestBody UpdateProductResource resource) { return ProductResourceFromEntityAssembler.toResource(commandService.handle(UpdateProductCommandFromResourceAssembler.toCommand(productId, resource))); }
    @DeleteMapping("/{productId}") @Operation(summary = "Delete product")
    public ResponseEntity<Void> delete(@PathVariable Long productId) { commandService.delete(productId); return ResponseEntity.noContent().build(); }

    private Long currentStoreId(String ownerEmail) {
        if (ownerEmail != null && !ownerEmail.isBlank()) {
            return accountRepository.findByOwnerEmail(ownerEmail).map(account -> account.getStoreId()).orElse(null);
        }
        return null;
    }
}
