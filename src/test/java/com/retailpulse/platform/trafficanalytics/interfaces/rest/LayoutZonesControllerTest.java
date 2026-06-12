package com.retailpulse.platform.trafficanalytics.interfaces.rest;

import com.retailpulse.platform.shared.application.result.Result;
import com.retailpulse.platform.trafficanalytics.application.commandservices.LayoutZoneCommandService;
import com.retailpulse.platform.trafficanalytics.application.queryservices.LayoutZoneQueryService;
import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.LayoutZone;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.CreateLayoutZoneCommand;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.UpdateLayoutZoneCommand;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetAllLayoutZonesQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetLayoutZoneByIdQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.valueobjects.ZoneType;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.List;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LayoutZonesControllerTest {
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new LayoutZonesController(new FakeCommandService(), new FakeQueryService())).build();

    @Test
    void getAllLayoutZonesReturnsOk() throws Exception {
        mockMvc.perform(get("/api/v1/zones")).andExpect(status().isOk());
    }

    private static class FakeQueryService implements LayoutZoneQueryService {
        public List<LayoutZone> handle(GetAllLayoutZonesQuery query) {
            return List.of(new LayoutZone("Z001", "Entrance", 20, 20, 180, 80, ZoneType.ACCESS));
        }

        public Optional<LayoutZone> handle(GetLayoutZoneByIdQuery query) {
            return Optional.empty();
        }
    }

    private static class FakeCommandService implements LayoutZoneCommandService {
        public Result<LayoutZone> handle(CreateLayoutZoneCommand command) {
            return null;
        }

        public Result<LayoutZone> handle(UpdateLayoutZoneCommand command) {
            return null;
        }
    }
}
