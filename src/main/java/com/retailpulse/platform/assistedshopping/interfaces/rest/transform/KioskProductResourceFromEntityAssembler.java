package com.retailpulse.platform.assistedshopping.interfaces.rest.transform;

import com.retailpulse.platform.storefoundation.domain.model.aggregates.Product;
import com.retailpulse.platform.assistedshopping.interfaces.rest.resources.KioskProductResource;
import com.retailpulse.platform.inventoryintelligence.domain.model.aggregates.InventoryItem;
import com.retailpulse.platform.promotionoptimization.domain.model.aggregates.PromotionRecommendation;
import com.retailpulse.platform.storefoundation.domain.model.entities.Zone;
import java.util.List;

public class KioskProductResourceFromEntityAssembler {
    public static KioskProductResource toResource(Product product) {
        return toResource(product, null, null, null, List.of());
    }

    public static KioskProductResource toResource(Product product, InventoryItem item, Zone zone, PromotionRecommendation recommendation, List<Zone> layoutZones) {
        String aisle = product.getLocation() == null ? null : product.getLocation().aisle();
        String shelf = product.getLocation() == null ? null : product.getLocation().shelf();
        String displayReference = product.getLocation() == null ? null : product.getLocation().displayReference();
        return new KioskProductResource(
            product.getId(),
            product.getStoreId(),
            product.getName(),
            product.getCategory(),
            product.getPrice(),
            product.getZoneId(),
            zone == null ? null : zone.getName(),
            item == null ? null : item.getAvailableStock(),
            item == null ? null : item.getStatus().name(),
            aisle,
            shelf,
            shelf == null ? displayReference : shelf + (displayReference == null ? "" : " - " + displayReference),
            displayReference,
            recommendation == null ? null : recommendation.getTitle(),
            zone == null ? null : zone.getX(),
            zone == null ? null : zone.getY(),
            zone == null ? null : zone.getWidth(),
            zone == null ? null : zone.getHeight(),
            layoutZones.stream()
                .map(layoutZone -> new com.retailpulse.platform.assistedshopping.interfaces.rest.resources.KioskLayoutZoneResource(
                    layoutZone.getId(),
                    layoutZone.getName(),
                    layoutZone.getType().name(),
                    layoutZone.getX(),
                    layoutZone.getY(),
                    layoutZone.getWidth(),
                    layoutZone.getHeight()
                ))
                .toList()
        );
    }
}
