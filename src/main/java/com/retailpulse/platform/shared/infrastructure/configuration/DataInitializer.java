package com.retailpulse.platform.shared.infrastructure.configuration;

import com.retailpulse.platform.inventoryintelligence.domain.model.aggregates.InventoryItem;
import com.retailpulse.platform.inventoryintelligence.domain.model.valueobjects.StockStatus;
import com.retailpulse.platform.inventoryintelligence.domain.repositories.InventoryItemRepository;
import com.retailpulse.platform.promotionoptimization.domain.model.aggregates.PromotionRecommendation;
import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.CommercialPriority;
import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.RecommendationStatus;
import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.RecommendationType;
import com.retailpulse.platform.promotionoptimization.domain.repositories.PromotionRecommendationRepository;
import com.retailpulse.platform.storefoundation.domain.model.aggregates.Product;
import com.retailpulse.platform.storefoundation.domain.model.aggregates.Store;
import com.retailpulse.platform.storefoundation.domain.model.entities.Zone;
import com.retailpulse.platform.storefoundation.domain.model.valueobjects.ProductLocation;
import com.retailpulse.platform.storefoundation.domain.model.valueobjects.ProductStatus;
import com.retailpulse.platform.storefoundation.domain.model.valueobjects.ZoneType;
import com.retailpulse.platform.storefoundation.domain.repositories.ProductRepository;
import com.retailpulse.platform.storefoundation.domain.repositories.StoreRepository;
import com.retailpulse.platform.storefoundation.domain.repositories.ZoneRepository;
import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalAlert;
import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalTask;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertStatus;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertType;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.PriorityLevel;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.TaskStatus;
import com.retailpulse.platform.storeoperations.domain.repositories.OperationalAlertRepository;
import com.retailpulse.platform.storeoperations.domain.repositories.OperationalTaskRepository;
import com.retailpulse.platform.subscription.domain.model.aggregates.SubscriptionPlan;
import com.retailpulse.platform.subscription.domain.model.valueobjects.BillingPeriod;
import com.retailpulse.platform.subscription.domain.model.valueobjects.PlanName;
import com.retailpulse.platform.subscription.domain.repositories.SubscriptionPlanRepository;
import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.ZoneMetric;
import com.retailpulse.platform.trafficanalytics.domain.model.valueobjects.CongestionStatus;
import com.retailpulse.platform.trafficanalytics.domain.model.valueobjects.HeatLevel;
import com.retailpulse.platform.trafficanalytics.domain.repositories.ZoneMetricRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Configuration
@Profile("dev")
public class DataInitializer {
    @Bean
    CommandLineRunner seedRetailPulseData(
        StoreRepository stores,
        ZoneRepository zones,
        ProductRepository products,
        InventoryItemRepository inventory,
        ZoneMetricRepository metrics,
        OperationalAlertRepository alerts,
        OperationalTaskRepository tasks,
        SubscriptionPlanRepository plans,
        PromotionRecommendationRepository recommendations
    ) {
        return args -> {
            if (stores.count() > 0) return;
            Store store = stores.save(new Store(null, "RetailPulse Demo Store", "Av. Primavera 123"));
            List<Zone> createdZones = List.of(
                zones.save(new Zone(null, store.getId(), "Entrance", ZoneType.ENTRANCE, 20)),
                zones.save(new Zone(null, store.getId(), "Main Aisle", ZoneType.AISLE, 35)),
                zones.save(new Zone(null, store.getId(), "Featured Display", ZoneType.DISPLAY, 15)),
                zones.save(new Zone(null, store.getId(), "Checkout", ZoneType.CHECKOUT, 10))
            );
            List<Product> createdProducts = List.of(
                products.save(new Product(null, store.getId(), "Organic Coffee", "COF-001", "Groceries", "Premium whole bean coffee", new BigDecimal("24.90"), ProductStatus.ACTIVE, createdZones.get(1).getId(), new ProductLocation("A1", "S1", "Coffee shelf"))),
                products.save(new Product(null, store.getId(), "Almond Milk", "MLK-002", "Beverages", "Unsweetened almond milk", new BigDecimal("12.50"), ProductStatus.ACTIVE, createdZones.get(1).getId(), new ProductLocation("A1", "S2", "Cold drinks"))),
                products.save(new Product(null, store.getId(), "Protein Bar", "BAR-003", "Snacks", "Chocolate protein bar", new BigDecimal("7.90"), ProductStatus.ACTIVE, createdZones.get(2).getId(), new ProductLocation("D1", "S1", "Promo display"))),
                products.save(new Product(null, store.getId(), "Olive Oil", "OIL-004", "Groceries", "Extra virgin olive oil", new BigDecimal("32.00"), ProductStatus.ACTIVE, createdZones.get(1).getId(), new ProductLocation("A2", "S3", "Cooking shelf"))),
                products.save(new Product(null, store.getId(), "Sparkling Water", "WTR-005", "Beverages", "Lemon sparkling water", new BigDecimal("4.50"), ProductStatus.ACTIVE, createdZones.get(3).getId(), new ProductLocation("C1", "S1", "Checkout fridge")))
            );
            createdProducts.forEach(product -> inventory.save(new InventoryItem(null, product.getId(), 20, 5, StockStatus.AVAILABLE)));
            metrics.save(new ZoneMetric(null, createdZones.get(0).getId(), 82, 2.5, 30, 0.40, HeatLevel.HIGH, CongestionStatus.MODERATE));
            metrics.save(new ZoneMetric(null, createdZones.get(1).getId(), 61, 4.2, 45, 0.25, HeatLevel.MEDIUM, CongestionStatus.LOW));
            metrics.save(new ZoneMetric(null, createdZones.get(2).getId(), 95, 6.1, 70, 0.18, HeatLevel.CRITICAL, CongestionStatus.HIGH));
            metrics.save(new ZoneMetric(null, createdZones.get(3).getId(), 44, 1.8, 22, 0.65, HeatLevel.MEDIUM, CongestionStatus.LOW));
            alerts.save(new OperationalAlert("A900", AlertType.CONGESTION, PriorityLevel.HIGH, AlertStatus.PENDING, "High activity detected in featured display.", "Z900", "Featured Display", "P900", "Demo product", Instant.parse("2026-05-04T09:00:00Z")));
            alerts.save(new OperationalAlert("A901", AlertType.LOW_STOCK, PriorityLevel.MEDIUM, AlertStatus.IN_PROGRESS, "Review checkout fridge stock.", "Z901", "Checkout", "P901", "Demo stock item", Instant.parse("2026-05-04T09:30:00Z")));
            alerts.save(new OperationalAlert("A902", AlertType.CUSTOMER_ASSISTANCE, PriorityLevel.MEDIUM, AlertStatus.PENDING, "Support requested near main aisle.", "Z902", "Main Aisle", "P902", "Assisted product", Instant.parse("2026-05-04T10:00:00Z")));
            tasks.save(new OperationalTask("T900", "Restock almond milk", "Move stock from storage to main aisle.", PriorityLevel.MEDIUM, TaskStatus.PENDING, "Z902", "Main Aisle", "A901", Instant.parse("2026-05-04T09:35:00Z")));
            tasks.save(new OperationalTask("T901", "Inspect featured display", "Check pricing and signage.", PriorityLevel.HIGH, TaskStatus.PENDING, "Z900", "Featured Display", "A900", Instant.parse("2026-05-04T09:05:00Z")));
            tasks.save(new OperationalTask("T902", "Clean checkout fridge", "Prepare fridge before afternoon peak.", PriorityLevel.LOW, TaskStatus.PENDING, "Z901", "Checkout", "A902", Instant.parse("2026-05-04T10:05:00Z")));
            plans.save(new SubscriptionPlan(null, PlanName.STARTER, new BigDecimal("49.00"), BillingPeriod.MONTHLY, 1, "Basic analytics for one store."));
            plans.save(new SubscriptionPlan(null, PlanName.GROWTH, new BigDecimal("129.00"), BillingPeriod.MONTHLY, 5, "Advanced analytics and operations."));
            plans.save(new SubscriptionPlan(null, PlanName.PREMIUM, new BigDecimal("299.00"), BillingPeriod.MONTHLY, 20, "Full RetailPulse intelligence suite."));
            recommendations.save(new PromotionRecommendation(null, createdProducts.get(2).getId(), "Bundle protein bars", "Pair with beverages near the display.", RecommendationType.BUNDLE, RecommendationStatus.ACTIVE, CommercialPriority.HIGH));
            recommendations.save(new PromotionRecommendation(null, createdProducts.get(3).getId(), "Relocate olive oil", "Move closer to high-traffic aisle.", RecommendationType.RELOCATION, RecommendationStatus.ACTIVE, CommercialPriority.MEDIUM));
            recommendations.save(new PromotionRecommendation(null, createdProducts.get(0).getId(), "Coffee discount", "Apply short promotion during morning traffic.", RecommendationType.DISCOUNT, RecommendationStatus.ACTIVE, CommercialPriority.HIGH));
        };
    }
}
