package com.retailpulse.platform.trafficanalytics.interfaces.rest;

import com.retailpulse.platform.trafficanalytics.application.commandservices.MovementEventCommandService;
import com.retailpulse.platform.trafficanalytics.application.commandservices.ZoneMetricCommandService;
import com.retailpulse.platform.trafficanalytics.application.queryservices.TrafficAnalyticsQueryService;
import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.ZoneMetric;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.resources.*;
import com.retailpulse.platform.trafficanalytics.interfaces.rest.transform.*;
import com.retailpulse.platform.storefoundation.domain.repositories.ZoneRepository;
import com.retailpulse.platform.subscription.domain.repositories.SaasAccountRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.Set;
import java.util.List;

@RestController
@RequestMapping("/api/v1/traffic")
@Tag(name = "Traffic Analytics")
public class TrafficAnalyticsController {
    private final TrafficAnalyticsQueryService queryService;
    private final MovementEventCommandService commandService;
    private final ZoneMetricCommandService zoneMetricCommandService;
    private final ZoneRepository zoneRepository;
    private final SaasAccountRepository accountRepository;
    public TrafficAnalyticsController(TrafficAnalyticsQueryService queryService, MovementEventCommandService commandService, ZoneMetricCommandService zoneMetricCommandService, ZoneRepository zoneRepository, SaasAccountRepository accountRepository) { this.queryService = queryService; this.commandService = commandService; this.zoneMetricCommandService = zoneMetricCommandService; this.zoneRepository = zoneRepository; this.accountRepository = accountRepository; }
    @GetMapping("/heatmap") @Operation(summary = "Get heatmap metrics")
    public List<HeatmapMetricResource> heatmap(@RequestHeader(value = "X-Owner-Email", required = false) String ownerEmail) { return filterByCurrentStore(queryService.heatmap(), ownerEmail).stream().map(HeatmapMetricResourceFromEntityAssembler::toResource).toList(); }
    @GetMapping("/zones/metrics") @Operation(summary = "Get zone metrics")
    public List<ZoneMetricResource> zones(@RequestHeader(value = "X-Owner-Email", required = false) String ownerEmail) { return filterByCurrentStore(queryService.zoneMetrics(), ownerEmail).stream().map(ZoneMetricResourceFromEntityAssembler::toResource).toList(); }
    @GetMapping("/congestion") @Operation(summary = "Get congestion metrics")
    public List<CongestionMetricResource> congestion(@RequestHeader(value = "X-Owner-Email", required = false) String ownerEmail) { return filterByCurrentStore(queryService.congestion(), ownerEmail).stream().map(CongestionMetricResourceFromEntityAssembler::toResource).toList(); }
    @PostMapping("/movement-events") @Operation(summary = "Register movement event")
    public void register(@Valid @RequestBody RegisterMovementEventResource resource) { commandService.handle(RegisterMovementEventCommandFromResourceAssembler.toCommand(resource)); }
    @PostMapping("/zones/{zoneId}/metrics") @Operation(summary = "Create or update zone metrics")
    public ZoneMetricResource upsertZoneMetric(@PathVariable Long zoneId, @Valid @RequestBody UpsertZoneMetricResource resource) { return ZoneMetricResourceFromEntityAssembler.toResource(zoneMetricCommandService.handle(UpsertZoneMetricCommandFromResourceAssembler.toCommand(zoneId, resource))); }
    @PutMapping("/zones/{zoneId}/metrics") @Operation(summary = "Create or update zone metrics")
    public ZoneMetricResource updateZoneMetric(@PathVariable Long zoneId, @Valid @RequestBody UpsertZoneMetricResource resource) { return ZoneMetricResourceFromEntityAssembler.toResource(zoneMetricCommandService.handle(UpsertZoneMetricCommandFromResourceAssembler.toCommand(zoneId, resource))); }

    private List<ZoneMetric> filterByCurrentStore(List<ZoneMetric> metrics, String ownerEmail) {
        Long storeId = currentStoreId(ownerEmail);
        if (storeId == null) return metrics;
        Set<Long> zoneIds = zoneRepository.findAll().stream()
            .filter(zone -> storeId.equals(zone.getStoreId()))
            .map(zone -> zone.getId())
            .collect(java.util.stream.Collectors.toSet());
        return metrics.stream().filter(metric -> zoneIds.contains(metric.getZoneId())).toList();
    }

    private Long currentStoreId(String ownerEmail) {
        if (ownerEmail != null && !ownerEmail.isBlank()) {
            return accountRepository.findByOwnerEmail(ownerEmail).map(account -> account.getStoreId()).orElse(null);
        }
        return null;
    }
}
