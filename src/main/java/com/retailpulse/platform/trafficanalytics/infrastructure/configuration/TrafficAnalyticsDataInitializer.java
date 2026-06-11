package com.retailpulse.platform.trafficanalytics.infrastructure.configuration;

import com.retailpulse.platform.trafficanalytics.application.commandservices.HeatmapMetricCommandService;
import com.retailpulse.platform.trafficanalytics.application.commandservices.LayoutZoneCommandService;
import com.retailpulse.platform.trafficanalytics.application.commandservices.StoreLayoutCommandService;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.CreateHeatmapMetricCommand;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.CreateLayoutZoneCommand;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.CreateStoreLayoutCommand;
import com.retailpulse.platform.trafficanalytics.domain.model.valueobjects.ZoneType;
import com.retailpulse.platform.trafficanalytics.domain.repositories.HeatmapMetricRepository;
import com.retailpulse.platform.trafficanalytics.domain.repositories.LayoutZoneRepository;
import com.retailpulse.platform.trafficanalytics.domain.repositories.StoreLayoutRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import java.util.List;

@Configuration
@Profile("dev")
public class TrafficAnalyticsDataInitializer {
    @Bean
    CommandLineRunner seedTrafficAnalyticsData(
        StoreLayoutCommandService storeLayoutCommandService,
        LayoutZoneCommandService layoutZoneCommandService,
        HeatmapMetricCommandService heatmapMetricCommandService,
        StoreLayoutRepository storeLayoutRepository,
        LayoutZoneRepository layoutZoneRepository,
        HeatmapMetricRepository heatmapMetricRepository
    ) {
        return args -> {
            if (!storeLayoutRepository.existsById("LAYOUT_MAIN")) {
                storeLayoutCommandService.handle(new CreateStoreLayoutCommand("LAYOUT_MAIN", "Main Store Layout", "RetailPulse Demo Store", 920, 460, true));
            }

            for (CreateLayoutZoneCommand command : layoutZones()) {
                if (!layoutZoneRepository.existsById(command.id())) {
                    layoutZoneCommandService.handle(command);
                }
            }

            for (CreateHeatmapMetricCommand command : heatmapMetrics()) {
                if (!heatmapMetricRepository.existsById(command.id())) {
                    heatmapMetricCommandService.handle(command);
                }
            }
        };
    }

    private List<CreateLayoutZoneCommand> layoutZones() {
        return List.of(
            new CreateLayoutZoneCommand("Z001", "Entrance", 20, 20, 180, 80, ZoneType.ACCESS),
            new CreateLayoutZoneCommand("Z002", "Checkout", 740, 20, 160, 80, ZoneType.CHECKOUT),
            new CreateLayoutZoneCommand("Z003", "Aisle 1", 20, 120, 280, 100, ZoneType.AISLE),
            new CreateLayoutZoneCommand("Z004", "Aisle 2", 320, 120, 280, 100, ZoneType.AISLE),
            new CreateLayoutZoneCommand("Z005", "Aisle 3", 620, 120, 280, 100, ZoneType.AISLE),
            new CreateLayoutZoneCommand("Z006", "Dairy Zone", 20, 240, 280, 100, ZoneType.SECTION),
            new CreateLayoutZoneCommand("Z007", "Grocery Zone", 320, 240, 280, 100, ZoneType.SECTION),
            new CreateLayoutZoneCommand("Z008", "Beverage Zone", 620, 240, 280, 100, ZoneType.SECTION),
            new CreateLayoutZoneCommand("Z009", "Promo Zone", 20, 360, 460, 80, ZoneType.PROMO)
        );
    }

    private List<CreateHeatmapMetricCommand> heatmapMetrics() {
        return List.of(
            new CreateHeatmapMetricCommand("HM001", "Z001", 340, 18, 12.0, 82, false),
            new CreateHeatmapMetricCommand("HM002", "Z002", 180, 30, 100.0, 15, false),
            new CreateHeatmapMetricCommand("HM003", "Z003", 290, 41, 28.0, 68, false),
            new CreateHeatmapMetricCommand("HM004", "Z004", 150, 35, 22.0, 35, false),
            new CreateHeatmapMetricCommand("HM005", "Z005", 90, 22, 10.0, 12, false),
            new CreateHeatmapMetricCommand("HM006", "Z006", 310, 45, 32.0, 75, true),
            new CreateHeatmapMetricCommand("HM007", "Z007", 170, 30, 18.0, 42, false),
            new CreateHeatmapMetricCommand("HM008", "Z008", 120, 25, 14.0, 28, false),
            new CreateHeatmapMetricCommand("HM009", "Z009", 410, 52, 35.0, 95, true)
        );
    }
}
