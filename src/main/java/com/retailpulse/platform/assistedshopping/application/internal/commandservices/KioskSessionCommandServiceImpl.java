package com.retailpulse.platform.assistedshopping.application.internal.commandservices;

import com.retailpulse.platform.assistedshopping.application.commandservices.KioskSessionCommandService;
import com.retailpulse.platform.assistedshopping.domain.model.aggregates.KioskSession;
import com.retailpulse.platform.assistedshopping.domain.model.commands.RegisterProductSearchCommand;
import com.retailpulse.platform.assistedshopping.domain.model.commands.StartKioskSessionCommand;
import com.retailpulse.platform.assistedshopping.domain.model.entities.ProductSearch;
import com.retailpulse.platform.assistedshopping.domain.model.valueobjects.SearchResultStatus;
import com.retailpulse.platform.assistedshopping.domain.model.valueobjects.SessionStatus;
import com.retailpulse.platform.assistedshopping.domain.repositories.KioskSessionRepository;
import com.retailpulse.platform.assistedshopping.domain.repositories.ProductSearchRepository;
import com.retailpulse.platform.storefoundation.domain.model.aggregates.Product;
import com.retailpulse.platform.storefoundation.domain.repositories.ProductRepository;
import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalAlert;
import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalTask;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertStatus;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.AlertType;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.PriorityLevel;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.TaskStatus;
import com.retailpulse.platform.storeoperations.domain.repositories.OperationalAlertRepository;
import com.retailpulse.platform.storeoperations.domain.repositories.OperationalTaskRepository;
import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.ZoneMetric;
import com.retailpulse.platform.trafficanalytics.domain.model.valueobjects.CongestionStatus;
import com.retailpulse.platform.trafficanalytics.domain.model.valueobjects.HeatLevel;
import com.retailpulse.platform.trafficanalytics.domain.repositories.ZoneMetricRepository;
import org.springframework.stereotype.Service;

@Service
public class KioskSessionCommandServiceImpl implements KioskSessionCommandService {
    private final KioskSessionRepository repository;
    private final ProductSearchRepository productSearchRepository;
    private final ProductRepository productRepository;
    private final OperationalAlertRepository alertRepository;
    private final OperationalTaskRepository taskRepository;
    private final ZoneMetricRepository zoneMetricRepository;

    public KioskSessionCommandServiceImpl(
        KioskSessionRepository repository,
        ProductSearchRepository productSearchRepository,
        ProductRepository productRepository,
        OperationalAlertRepository alertRepository,
        OperationalTaskRepository taskRepository,
        ZoneMetricRepository zoneMetricRepository
    ) {
        this.repository = repository;
        this.productSearchRepository = productSearchRepository;
        this.productRepository = productRepository;
        this.alertRepository = alertRepository;
        this.taskRepository = taskRepository;
        this.zoneMetricRepository = zoneMetricRepository;
    }

    public KioskSession handle(StartKioskSessionCommand command) { return repository.save(new KioskSession(null, command.storeId(), SessionStatus.ACTIVE)); }
    public ProductSearch handle(RegisterProductSearchCommand command) {
        String action = command.action() == null || command.action().isBlank() ? "SEARCHED" : command.action();
        ProductSearch search = productSearchRepository.save(new ProductSearch(null, command.sessionId(), command.query(), command.productId(), command.productId() == null ? SearchResultStatus.NOT_FOUND : SearchResultStatus.FOUND, action));
        if ("HELP_REQUESTED".equalsIgnoreCase(action)) {
            createAssistanceWork(search);
        }
        updateZoneConversionMetrics(search, action);
        return search;
    }

    private void createAssistanceWork(ProductSearch search) {
        Product product = search.getProductId() == null ? null : productRepository.findById(search.getProductId()).orElse(null);
        Long storeId = product != null ? product.getStoreId() : null;
        Long zoneId = product != null ? product.getZoneId() : null;
        String productName = product != null ? product.getName() : search.getQuery();
        String reason = "Kiosk shopper requested staff assistance for " + productName + " after searching '" + search.getQuery() + "'.";
        OperationalAlert alert = alertRepository.save(new OperationalAlert(null, storeId, "Customer assistance requested", reason, AlertType.ASSISTANCE, AlertStatus.ACTIVE, PriorityLevel.MEDIUM, search.getProductId(), zoneId, "KIOSK_SEARCH", reason));
        taskRepository.save(new OperationalTask(null, storeId, "Assist shopper with " + productName, "Go to the product zone and help the shopper locate the item.", TaskStatus.PENDING, PriorityLevel.MEDIUM, alert.getId(), search.getProductId(), zoneId, "KIOSK_SEARCH", reason));
    }

    private void updateZoneConversionMetrics(ProductSearch search, String action) {
        Product product = search.getProductId() == null ? null : productRepository.findById(search.getProductId()).orElse(null);
        if (product == null || product.getZoneId() == null) return;

        ZoneMetric metric = zoneMetricRepository.findByZoneId(product.getZoneId())
            .orElse(new ZoneMetric(null, product.getZoneId(), 0, 0.0, 0, 0.0, HeatLevel.COLD, CongestionStatus.LOW));
        int currentInteractions = metric.getInteractionCount() == null ? 0 : metric.getInteractionCount();
        double currentRate = metric.getConversionRate() == null ? 0.0 : metric.getConversionRate();
        int currentConversions = (int) Math.round(currentInteractions * currentRate);
        int nextInteractions = currentInteractions + 1;
        int nextConversions = currentConversions + ("FOUND".equalsIgnoreCase(action) ? 1 : 0);

        metric.setInteractionCount(nextInteractions);
        metric.setConversionRate(nextInteractions == 0 ? 0.0 : nextConversions / (double) nextInteractions);
        if (metric.getTrafficCount() == null) metric.setTrafficCount(nextInteractions);
        if (metric.getAverageDwellTime() == null) metric.setAverageDwellTime(0.0);
        if (metric.getHeatLevel() == null) metric.setHeatLevel(HeatLevel.COLD);
        if (metric.getCongestionStatus() == null) metric.setCongestionStatus(CongestionStatus.LOW);
        zoneMetricRepository.save(metric);
    }
}
