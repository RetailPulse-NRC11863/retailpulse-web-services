package com.retailpulse.platform.shared.infrastructure.configuration;

import com.retailpulse.platform.assistedshopping.domain.model.aggregates.KioskSession;
import com.retailpulse.platform.assistedshopping.domain.model.entities.ProductSearch;
import com.retailpulse.platform.assistedshopping.domain.model.valueobjects.SearchResultStatus;
import com.retailpulse.platform.assistedshopping.domain.model.valueobjects.SessionStatus;
import com.retailpulse.platform.assistedshopping.domain.repositories.KioskSessionRepository;
import com.retailpulse.platform.assistedshopping.domain.repositories.ProductSearchRepository;
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
import com.retailpulse.platform.subscription.domain.model.aggregates.SaasAccount;
import com.retailpulse.platform.subscription.domain.model.aggregates.SubscriptionPlan;
import com.retailpulse.platform.subscription.domain.model.valueobjects.BillingPeriod;
import com.retailpulse.platform.subscription.domain.model.valueobjects.PlanName;
import com.retailpulse.platform.subscription.domain.model.valueobjects.SubscriptionStatus;
import com.retailpulse.platform.subscription.domain.repositories.SaasAccountRepository;
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
import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile({"dev", "seed"})
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
        SaasAccountRepository accounts,
        PromotionRecommendationRepository recommendations,
        KioskSessionRepository kioskSessions,
        ProductSearchRepository productSearches
    ) {
        return args -> {
            if (plans.count() == 0) {
                seedSubscriptionPlans(plans);
            }

            Store store = stores.findAll().stream()
                .findFirst()
                .orElseGet(() -> stores.save(new Store(null, "RetailPulse Demo Market", "Av. Primavera 123, Lima", "Jesus Andres", "ACTIVE")));

            if (accounts.count() == 0) {
                seedSaasAccount(accounts, plans, store);
            }

            List<Zone> createdZones = seedZones(store, zones);

            seedMetrics(metrics, createdZones);

            List<Product> createdProducts = seedProducts(store, createdZones, products, inventory);

            if (createdProducts.size() >= 22 && alerts.count() == 0 && tasks.count() == 0) {
                seedOperations(store, createdZones, createdProducts, alerts, tasks);
            }
            if (createdProducts.size() >= 22) {
                seedRecommendations(createdProducts, recommendations);
            }
            if (createdProducts.size() >= 19) {
                seedKioskActivity(store, createdProducts, kioskSessions, productSearches);
            }
        };
    }

    private List<Zone> seedZones(Store store, ZoneRepository zones) {
        return List.of(
            ensureZone(store, zones, "Main Entrance", ZoneType.ENTRANCE, 25, 30, 35, 150, 80),
            ensureZone(store, zones, "Cold Beverages", ZoneType.AISLE, 30, 220, 35, 180, 90),
            ensureZone(store, zones, "Snacks and Cookies", ZoneType.AISLE, 30, 430, 35, 190, 90),
            ensureZone(store, zones, "Dairy and Refrigerated", ZoneType.AISLE, 25, 650, 35, 210, 90),
            ensureZone(store, zones, "Pantry Essentials", ZoneType.AISLE, 35, 80, 165, 230, 95),
            ensureZone(store, zones, "Personal Care", ZoneType.AISLE, 25, 345, 165, 190, 95),
            ensureZone(store, zones, "Household Cleaning", ZoneType.AISLE, 25, 570, 165, 220, 95),
            ensureZone(store, zones, "Promotional Display", ZoneType.DISPLAY, 20, 110, 305, 260, 85),
            ensureZone(store, zones, "Checkout Impulse Area", ZoneType.CHECKOUT, 18, 440, 305, 190, 85)
        );
    }

    private Zone ensureZone(Store store, ZoneRepository zones, String name, ZoneType type, int capacity, int x, int y, int width, int height) {
        return zones.findAll().stream()
            .filter(zone -> store.getId().equals(zone.getStoreId()) && name.equalsIgnoreCase(zone.getName()))
            .findFirst()
            .orElseGet(() -> zones.save(new Zone(null, store.getId(), name, type, capacity, x, y, width, height)));
    }

    private void seedMetrics(ZoneMetricRepository metrics, List<Zone> zones) {
        ensureMetric(metrics, zones.get(0).getId(), 95, 16.0, 95, 0.18, HeatLevel.COLD, CongestionStatus.LOW);
        ensureMetric(metrics, zones.get(1).getId(), 145, 42.0, 145, 0.48, HeatLevel.WARM, CongestionStatus.MODERATE);
        ensureMetric(metrics, zones.get(2).getId(), 185, 55.0, 185, 0.38, HeatLevel.HOT, CongestionStatus.MODERATE);
        ensureMetric(metrics, zones.get(3).getId(), 120, 36.0, 120, 0.52, HeatLevel.WARM, CongestionStatus.MODERATE);
        ensureMetric(metrics, zones.get(4).getId(), 78, 64.0, 78, 0.44, HeatLevel.COLD, CongestionStatus.LOW);
        ensureMetric(metrics, zones.get(5).getId(), 68, 70.0, 68, 0.31, HeatLevel.COLD, CongestionStatus.LOW);
        ensureMetric(metrics, zones.get(6).getId(), 52, 58.0, 52, 0.28, HeatLevel.COLD, CongestionStatus.LOW);
        ensureMetric(metrics, zones.get(7).getId(), 230, 82.0, 230, 0.24, HeatLevel.HOT, CongestionStatus.HIGH);
        ensureMetric(metrics, zones.get(8).getId(), 170, 25.0, 170, 0.61, HeatLevel.WARM, CongestionStatus.MODERATE);
    }

    private void ensureMetric(ZoneMetricRepository metrics, Long zoneId, int traffic, double dwellTime, int interactions, double conversionRate, HeatLevel heatLevel, CongestionStatus congestionStatus) {
        ZoneMetric metric = metrics.findByZoneId(zoneId)
            .orElse(new ZoneMetric(null, zoneId, traffic, dwellTime, interactions, conversionRate, heatLevel, congestionStatus));
        metric.setTrafficCount(traffic);
        metric.setAverageDwellTime(dwellTime);
        metric.setInteractionCount(interactions);
        metric.setConversionRate(conversionRate);
        metric.setHeatLevel(heatLevel);
        metric.setCongestionStatus(congestionStatus);
        metrics.save(metric);
    }

    private List<Product> seedProducts(Store store, List<Zone> zones, ProductRepository products, InventoryItemRepository inventory) {
        List<ProductSeed> seeds = List.of(
            new ProductSeed("Mineral Water 625ml", "BEV-001", "Beverages", "Cold bottled mineral water", "2.50", 1, "CB", "A1", "Cold Beverages shelf", 30, 8),
            new ProductSeed("Cola Soda 500ml", "BEV-002", "Beverages", "Single-serve cola soda", "3.80", 1, "CB", "A2", "Cold Beverages shelf", 18, 6),
            new ProductSeed("Orange Juice 1L", "BEV-003", "Beverages", "Fresh orange juice carton", "8.90", 1, "CB", "B1", "Juice shelf", 9, 5),
            new ProductSeed("Wavy Potato Chips", "SNK-001", "Snacks", "Salted wavy potato chips", "6.50", 2, "SC", "A1", "Snacks shelf", 22, 5),
            new ProductSeed("Chocolate Cookies", "SNK-002", "Snacks", "Chocolate sandwich cookies", "4.90", 2, "SC", "A2", "Cookies shelf", 4, 5),
            new ProductSeed("Granola Bar", "SNK-003", "Snacks", "Oat granola bar", "3.20", 2, "SC", "B1", "Healthy snacks shelf", 16, 5),
            new ProductSeed("Evaporated Milk", "DRY-001", "Dairy", "Evaporated milk can", "4.70", 3, "DR", "A1", "Dairy shelf", 3, 5),
            new ProductSeed("Natural Yogurt", "DRY-002", "Dairy", "Natural yogurt cup", "5.60", 3, "DR", "A2", "Refrigerated shelf", 12, 4),
            new ProductSeed("Sliced Cheese", "DRY-003", "Dairy", "Pack of sliced cheese", "9.90", 3, "DR", "B1", "Cheese shelf", 7, 4),
            new ProductSeed("Extra Rice 1kg", "PAN-001", "Groceries", "Extra grade rice bag", "5.80", 4, "PE", "A1", "Rice shelf", 25, 7),
            new ProductSeed("Vegetable Oil 1L", "PAN-002", "Groceries", "Vegetable cooking oil", "9.70", 4, "PE", "A2", "Oil shelf", 0, 5),
            new ProductSeed("Spaghetti Pasta 500g", "PAN-003", "Groceries", "Spaghetti pasta pack", "3.90", 4, "PE", "B1", "Pasta shelf", 14, 5),
            new ProductSeed("Family Shampoo", "PC-001", "Personal Care", "Family-size shampoo bottle", "16.90", 5, "PC", "A1", "Hair care shelf", 9, 3),
            new ProductSeed("Toothpaste Mint", "PC-002", "Personal Care", "Mint toothpaste tube", "7.50", 5, "PC", "A2", "Oral care shelf", 11, 3),
            new ProductSeed("Antibacterial Soap", "PC-003", "Personal Care", "Antibacterial soap bar", "4.30", 5, "PC", "B1", "Soap shelf", 20, 6),
            new ProductSeed("Liquid Detergent", "HC-001", "Household Cleaning", "Liquid laundry detergent", "18.50", 6, "HC", "A1", "Detergent shelf", 6, 4),
            new ProductSeed("Bleach 1L", "HC-002", "Household Cleaning", "Household bleach bottle", "4.80", 6, "HC", "A2", "Cleaning shelf", 8, 4),
            new ProductSeed("Paper Towels", "HC-003", "Household Cleaning", "Paper towel roll pack", "12.90", 6, "HC", "B1", "Paper goods shelf", 5, 4),
            new ProductSeed("Promotional Snack Pack", "PD-001", "Promotional Display", "Bundle of selected snacks", "11.90", 7, "PD", "A1", "Promotion display", 15, 5),
            new ProductSeed("Energy Drink Combo", "PD-002", "Promotional Display", "Two energy drinks combo", "13.50", 7, "PD", "A2", "Promotion display", 10, 5),
            new ProductSeed("Breakfast Bundle", "PD-003", "Promotional Display", "Cereal and milk breakfast bundle", "18.90", 7, "PD", "B1", "Promotion display", 6, 5),
            new ProductSeed("Chocolate Bar", "CI-001", "Checkout Impulse Area", "Milk chocolate bar", "3.50", 8, "CI", "A1", "Checkout shelf", 35, 8),
            new ProductSeed("Reusable Shopping Bag", "CI-002", "Checkout Impulse Area", "Reusable shopping bag", "2.00", 8, "CI", "A2", "Checkout hanger", 40, 10),
            new ProductSeed("Mint Candy", "CI-003", "Checkout Impulse Area", "Mint candy pack", "1.50", 8, "CI", "B1", "Checkout shelf", 28, 8)
        );

        List<Product> createdProducts = new ArrayList<>();
        for (ProductSeed seed : seeds) {
            StockStatus stockStatus = stockStatus(seed.stock(), seed.threshold());
            ProductStatus productStatus = stockStatus == StockStatus.OUT_OF_STOCK ? ProductStatus.OUT_OF_STOCK : ProductStatus.ACTIVE;
            Product product = products.findAll().stream()
                .filter(existing -> store.getId().equals(existing.getStoreId()) && seed.sku().equalsIgnoreCase(existing.getSku()))
                .findFirst()
                .orElseGet(() -> products.save(new Product(null, store.getId(), seed.name(), seed.sku(), seed.category(), seed.description(), new BigDecimal(seed.price()), productStatus, zones.get(seed.zoneIndex()).getId(), new ProductLocation(seed.aisle(), seed.shelf(), seed.displayReference()))));
            if (inventory.findByProductId(product.getId()).isEmpty()) {
                inventory.save(new InventoryItem(null, product.getId(), seed.stock(), seed.threshold(), stockStatus));
            }
            createdProducts.add(product);
        }
        return createdProducts;
    }

    private void seedOperations(Store store, List<Zone> zones, List<Product> products, OperationalAlertRepository alerts, OperationalTaskRepository tasks) {
        OperationalAlert displayAlert = alerts.save(new OperationalAlert(null, store.getId(), "Promotional display conversion gap", "High traffic is not converting in the promotional display.", AlertType.COMMERCIAL, AlertStatus.ACTIVE, PriorityLevel.HIGH, products.get(18).getId(), zones.get(7).getId(), "PROMOTION_OPPORTUNITY_POLICY", "Promotional Display has 210 visitors, 82 seconds dwell time and only 24% conversion."));
        OperationalAlert oilAlert = alerts.save(new OperationalAlert(null, store.getId(), "Vegetable Oil out of stock", "Vegetable Oil 1L stock changed to 0 units. Critical threshold is 5.", AlertType.STOCK, AlertStatus.ACTIVE, PriorityLevel.HIGH, products.get(10).getId(), zones.get(4).getId(), "INVENTORY_STOCK_POLICY", "Vegetable Oil 1L stock changed to 0 units. Critical threshold is 5."));
        OperationalAlert milkAlert = alerts.save(new OperationalAlert(null, store.getId(), "Evaporated Milk below threshold", "Evaporated Milk stock changed to 3 units. Critical threshold is 5.", AlertType.STOCK, AlertStatus.ACTIVE, PriorityLevel.MEDIUM, products.get(6).getId(), zones.get(3).getId(), "INVENTORY_STOCK_POLICY", "Evaporated Milk stock changed to 3 units. Critical threshold is 5."));
        tasks.save(new OperationalTask(null, store.getId(), "Review promotional snack display", "Check product visibility, signage and bundle positioning.", TaskStatus.PENDING, PriorityLevel.HIGH, displayAlert.getId(), products.get(18).getId(), zones.get(7).getId(), "PROMOTION_OPPORTUNITY_POLICY", displayAlert.getTriggerReason()));
        tasks.save(new OperationalTask(null, store.getId(), "Restock Vegetable Oil 1L", "Move reserve units from storage to Pantry Essentials and update stock.", TaskStatus.PENDING, PriorityLevel.HIGH, oilAlert.getId(), products.get(10).getId(), zones.get(4).getId(), "INVENTORY_STOCK_POLICY", oilAlert.getTriggerReason()));
        tasks.save(new OperationalTask(null, store.getId(), "Restock Evaporated Milk", "Move units from Back Storage to Dairy and Refrigerated.", TaskStatus.PENDING, PriorityLevel.MEDIUM, milkAlert.getId(), products.get(6).getId(), zones.get(3).getId(), "INVENTORY_STOCK_POLICY", milkAlert.getTriggerReason()));
    }

    private void seedSubscriptionPlans(SubscriptionPlanRepository plans) {
        plans.save(new SubscriptionPlan(null, PlanName.STARTER, new BigDecimal("49.00"), BillingPeriod.MONTHLY, 1, "Basic analytics for one store."));
        plans.save(new SubscriptionPlan(null, PlanName.GROWTH, new BigDecimal("129.00"), BillingPeriod.MONTHLY, 5, "Advanced analytics and operations."));
        plans.save(new SubscriptionPlan(null, PlanName.PREMIUM, new BigDecimal("299.00"), BillingPeriod.MONTHLY, 20, "Full RetailPulse intelligence suite."));
    }

    private void seedSaasAccount(SaasAccountRepository accounts, SubscriptionPlanRepository plans, Store store) {
        SubscriptionPlan plan = plans.findAll().stream()
            .filter(p -> p.getName() == PlanName.PREMIUM)
            .findFirst()
            .orElseGet(() -> plans.findAll().get(0));
        accounts.save(new SaasAccount(null, store.getId(), "admin@retailpulse.com", plan.getId(), SubscriptionStatus.ACTIVE));
    }

    private void seedRecommendations(List<Product> products, PromotionRecommendationRepository recommendations) {
        ensureRecommendation(recommendations, products.get(18).getId(), "Improve snack pack conversion", "Move the bundle to eye level and pair it with a beverage offer.", RecommendationType.BUNDLE, CommercialPriority.HIGH);
        ensureRecommendation(recommendations, products.get(20).getId(), "Reposition breakfast bundle", "Relocate the breakfast bundle closer to Dairy and Refrigerated.", RecommendationType.RELOCATION, CommercialPriority.MEDIUM);
        ensureRecommendation(recommendations, products.get(21).getId(), "Checkout chocolate impulse offer", "Keep chocolate bars near checkout because impulse conversion is strong.", RecommendationType.DISCOUNT, CommercialPriority.MEDIUM);
    }

    private void ensureRecommendation(PromotionRecommendationRepository recommendations, Long productId, String title, String description, RecommendationType type, CommercialPriority priority) {
        boolean exists = recommendations.findAll().stream()
            .anyMatch(recommendation -> productId.equals(recommendation.getProductId()) && title.equalsIgnoreCase(recommendation.getTitle()));
        if (!exists) {
            recommendations.save(new PromotionRecommendation(null, productId, title, description, type, RecommendationStatus.ACTIVE, priority));
        }
    }

    private void seedKioskActivity(Store store, List<Product> products, KioskSessionRepository sessions, ProductSearchRepository searches) {
        List<SearchSeed> seeds = List.of(
            new SearchSeed("oil", products.get(10).getId(), SearchResultStatus.FOUND, "SEARCHED"),
            new SearchSeed("vegetable oil", products.get(10).getId(), SearchResultStatus.FOUND, "LOCATION_VIEWED"),
            new SearchSeed("milk", products.get(6).getId(), SearchResultStatus.FOUND, "SEARCHED"),
            new SearchSeed("evaporated milk", products.get(6).getId(), SearchResultStatus.FOUND, "HELP_REQUESTED"),
            new SearchSeed("chips", products.get(3).getId(), SearchResultStatus.FOUND, "SEARCHED"),
            new SearchSeed("chips", products.get(3).getId(), SearchResultStatus.FOUND, "FOUND"),
            new SearchSeed("snack pack", products.get(18).getId(), SearchResultStatus.FOUND, "LOCATION_VIEWED"),
            new SearchSeed("snack pack", products.get(18).getId(), SearchResultStatus.FOUND, "SEARCHED")
        );
        boolean hasMissingSearch = seeds.stream().anyMatch(seed -> isMissingSearch(searches, seed));
        if (!hasMissingSearch) return;

        KioskSession session = sessions.save(new KioskSession(null, store.getId(), SessionStatus.ACTIVE));
        seeds.stream()
            .filter(seed -> isMissingSearch(searches, seed))
            .forEach(seed -> searches.save(new ProductSearch(null, session.getId(), seed.query(), seed.productId(), seed.status(), seed.action())));
    }

    private boolean isMissingSearch(ProductSearchRepository searches, SearchSeed seed) {
        return searches.findByProductId(seed.productId()).stream()
            .noneMatch(search -> seed.query().equalsIgnoreCase(search.getQuery()) && seed.action().equalsIgnoreCase(search.getAction()));
    }

    private StockStatus stockStatus(int stock, int threshold) {
        if (stock <= 0) return StockStatus.OUT_OF_STOCK;
        if (stock <= threshold) return StockStatus.LOW_STOCK;
        return StockStatus.AVAILABLE;
    }

    private record ProductSeed(String name, String sku, String category, String description, String price, int zoneIndex, String aisle, String shelf, String displayReference, int stock, int threshold) {
    }

    private record SearchSeed(String query, Long productId, SearchResultStatus status, String action) {
    }
}
