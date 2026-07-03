package com.retailpulse.platform.promotionoptimization.application.internal.commandservices;

import com.retailpulse.platform.promotionoptimization.application.commandservices.PromotionRecommendationCommandService;
import com.retailpulse.platform.promotionoptimization.domain.model.aggregates.PromotionRecommendation;
import com.retailpulse.platform.promotionoptimization.domain.model.commands.ApplyPromotionRecommendationCommand;
import com.retailpulse.platform.promotionoptimization.domain.model.commands.CreatePromotionRecommendationCommand;
import com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.RecommendationStatus;
import com.retailpulse.platform.promotionoptimization.domain.repositories.PromotionRecommendationRepository;
import com.retailpulse.platform.storefoundation.domain.model.aggregates.Product;
import com.retailpulse.platform.storefoundation.domain.repositories.ProductRepository;
import com.retailpulse.platform.storeoperations.domain.model.aggregates.OperationalTask;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.PriorityLevel;
import com.retailpulse.platform.storeoperations.domain.model.valueobjects.TaskStatus;
import com.retailpulse.platform.storeoperations.domain.repositories.OperationalTaskRepository;
import org.springframework.stereotype.Service;

@Service
public class PromotionRecommendationCommandServiceImpl implements PromotionRecommendationCommandService {
    private final PromotionRecommendationRepository repository;
    private final ProductRepository productRepository;
    private final OperationalTaskRepository taskRepository;
    public PromotionRecommendationCommandServiceImpl(PromotionRecommendationRepository repository, ProductRepository productRepository, OperationalTaskRepository taskRepository) { this.repository = repository; this.productRepository = productRepository; this.taskRepository = taskRepository; }
    public PromotionRecommendation handle(CreatePromotionRecommendationCommand c) { return repository.save(new PromotionRecommendation(null, c.productId(), c.title(), c.description(), c.type(), RecommendationStatus.ACTIVE, c.priority())); }
    public PromotionRecommendation handle(ApplyPromotionRecommendationCommand c) {
        PromotionRecommendation r = repository.findById(c.recommendationId()).orElseThrow(() -> new IllegalArgumentException("Recommendation not found"));
        boolean shouldCreateTask = r.getStatus() != RecommendationStatus.APPLIED;
        r.apply();
        Product product = productRepository.findById(r.getProductId()).orElse(null);
        if (shouldCreateTask && product != null) {
            taskRepository.save(new OperationalTask(
                null,
                product.getStoreId(),
                "Apply recommendation for " + product.getName(),
                r.getDescription(),
                TaskStatus.PENDING,
                toPriority(r.getPriority()),
                null,
                product.getId(),
                product.getZoneId(),
                "PROMOTION_RECOMMENDATION",
                "Recommendation '" + r.getTitle() + "' was applied by the administrator."
            ));
        }
        return repository.save(r);
    }

    private PriorityLevel toPriority(com.retailpulse.platform.promotionoptimization.domain.model.valueobjects.CommercialPriority priority) {
        return switch (priority) {
            case HIGH -> PriorityLevel.HIGH;
            case MEDIUM -> PriorityLevel.MEDIUM;
            default -> PriorityLevel.LOW;
        };
    }
}
