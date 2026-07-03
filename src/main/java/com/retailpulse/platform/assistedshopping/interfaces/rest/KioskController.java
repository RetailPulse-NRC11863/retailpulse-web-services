package com.retailpulse.platform.assistedshopping.interfaces.rest;

import com.retailpulse.platform.assistedshopping.application.commandservices.KioskSessionCommandService;
import com.retailpulse.platform.assistedshopping.application.queryservices.KioskSessionQueryService;
import com.retailpulse.platform.inventoryintelligence.domain.repositories.InventoryItemRepository;
import com.retailpulse.platform.promotionoptimization.domain.model.aggregates.PromotionRecommendation;
import com.retailpulse.platform.promotionoptimization.domain.repositories.PromotionRecommendationRepository;
import com.retailpulse.platform.assistedshopping.interfaces.rest.resources.*;
import com.retailpulse.platform.assistedshopping.interfaces.rest.transform.*;
import com.retailpulse.platform.storefoundation.domain.model.aggregates.Product;
import com.retailpulse.platform.storefoundation.domain.repositories.ZoneRepository;
import com.retailpulse.platform.subscription.domain.repositories.SaasAccountRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/kiosk")
@Tag(name = "Kiosk")
public class KioskController {
    private final KioskSessionCommandService commandService;
    private final KioskSessionQueryService queryService;
    private final InventoryItemRepository inventoryItemRepository;
    private final ZoneRepository zoneRepository;
    private final PromotionRecommendationRepository recommendationRepository;
    private final SaasAccountRepository accountRepository;

    public KioskController(KioskSessionCommandService commandService, KioskSessionQueryService queryService, InventoryItemRepository inventoryItemRepository, ZoneRepository zoneRepository, PromotionRecommendationRepository recommendationRepository, SaasAccountRepository accountRepository) {
        this.commandService = commandService;
        this.queryService = queryService;
        this.inventoryItemRepository = inventoryItemRepository;
        this.zoneRepository = zoneRepository;
        this.recommendationRepository = recommendationRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/products/search") @Operation(summary = "Search kiosk products")
    public List<KioskProductResource> search(@RequestParam String query, @RequestHeader(value = "X-Owner-Email", required = false) String ownerEmail) {
        Long storeId = currentStoreId(ownerEmail);
        return queryService.searchProducts(query).stream()
            .filter(product -> storeId == null || storeId.equals(product.getStoreId()))
            .map(this::toKioskProductResource).toList();
    }
    @GetMapping("/products/{productId}") @Operation(summary = "Get kiosk product by id")
    public ResponseEntity<KioskProductResource> product(@PathVariable Long productId, @RequestHeader(value = "X-Owner-Email", required = false) String ownerEmail) {
        Long storeId = currentStoreId(ownerEmail);
        return queryService.getProductById(productId)
            .filter(product -> storeId == null || storeId.equals(product.getStoreId()))
            .map(this::toKioskProductResource).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/sessions") @Operation(summary = "Start kiosk session")
    public KioskSessionResource start(@Valid @RequestBody StartKioskSessionResource resource) { return KioskSessionResourceFromEntityAssembler.toResource(commandService.handle(StartKioskSessionCommandFromResourceAssembler.toCommand(resource))); }
    @PostMapping("/sessions/{sessionId}/searches") @Operation(summary = "Register product search")
    public void registerSearch(@PathVariable Long sessionId, @Valid @RequestBody RegisterProductSearchResource resource) { commandService.handle(RegisterProductSearchCommandFromResourceAssembler.toCommand(sessionId, resource)); }
    @GetMapping("/sessions/{sessionId}") @Operation(summary = "Get kiosk session")
    public ResponseEntity<KioskSessionResource> session(@PathVariable Long sessionId) { return queryService.getSessionById(sessionId).map(KioskSessionResourceFromEntityAssembler::toResource).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    private KioskProductResource toKioskProductResource(Product product) {
        PromotionRecommendation recommendation = recommendationRepository.findAll().stream()
            .filter(r -> product.getId().equals(r.getProductId()))
            .findFirst()
            .orElse(null);
        return KioskProductResourceFromEntityAssembler.toResource(
            product,
            inventoryItemRepository.findByProductId(product.getId()).orElse(null),
            product.getZoneId() == null ? null : zoneRepository.findById(product.getZoneId()).orElse(null),
            recommendation,
            zoneRepository.findAll().stream()
                .filter(zone -> product.getStoreId() != null && product.getStoreId().equals(zone.getStoreId()))
                .toList()
        );
    }

    private Long currentStoreId(String ownerEmail) {
        if (ownerEmail != null && !ownerEmail.isBlank()) {
            return accountRepository.findByOwnerEmail(ownerEmail).map(account -> account.getStoreId()).orElse(null);
        }
        return null;
    }
}
