package com.retailpulse.platform.trafficanalytics.interfaces.rest;

import com.retailpulse.platform.shared.application.result.Result;
import com.retailpulse.platform.trafficanalytics.application.commandservices.StoreLayoutCommandService;
import com.retailpulse.platform.trafficanalytics.application.queryservices.LayoutZoneQueryService;
import com.retailpulse.platform.trafficanalytics.application.queryservices.StoreLayoutQueryService;
import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.LayoutZone;
import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.StoreLayout;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.CreateStoreLayoutCommand;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.UpdateStoreLayoutCommand;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetAllLayoutZonesQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetAllStoreLayoutsQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetCurrentStoreLayoutQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetLayoutZoneByIdQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetStoreLayoutByIdQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.valueobjects.ZoneType;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.List;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StoreLayoutsControllerTest {
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new StoreLayoutsController(new FakeCommandService(), new FakeStoreLayoutQueryService(), new FakeLayoutZoneQueryService())).build();

    @Test
    void getCurrentStoreLayoutReturnsOk() throws Exception {
        mockMvc.perform(get("/api/v1/layouts/current")).andExpect(status().isOk());
    }

    private static class FakeStoreLayoutQueryService implements StoreLayoutQueryService {
        public List<StoreLayout> handle(GetAllStoreLayoutsQuery query) {
            return List.of(current());
        }

        public Optional<StoreLayout> handle(GetStoreLayoutByIdQuery query) {
            return Optional.of(current());
        }

        public Optional<StoreLayout> handle(GetCurrentStoreLayoutQuery query) {
            return Optional.of(current());
        }

        private StoreLayout current() {
            return new StoreLayout("LAYOUT_MAIN", "Main Store Layout", "RetailPulse Demo Store", 920, 460, true);
        }
    }

    private static class FakeLayoutZoneQueryService implements LayoutZoneQueryService {
        public List<LayoutZone> handle(GetAllLayoutZonesQuery query) {
            return List.of(new LayoutZone("Z001", "Entrance", 20, 20, 180, 80, ZoneType.ACCESS));
        }

        public Optional<LayoutZone> handle(GetLayoutZoneByIdQuery query) {
            return Optional.empty();
        }
    }

    private static class FakeCommandService implements StoreLayoutCommandService {
        public Result<StoreLayout> handle(CreateStoreLayoutCommand command) {
            return null;
        }

        public Result<StoreLayout> handle(UpdateStoreLayoutCommand command) {
            return null;
        }
    }
}
