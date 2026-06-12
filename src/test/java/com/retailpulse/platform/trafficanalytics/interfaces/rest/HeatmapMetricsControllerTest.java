package com.retailpulse.platform.trafficanalytics.interfaces.rest;

import com.retailpulse.platform.shared.application.result.Result;
import com.retailpulse.platform.trafficanalytics.application.commandservices.HeatmapMetricCommandService;
import com.retailpulse.platform.trafficanalytics.application.queryservices.HeatmapMetricQueryService;
import com.retailpulse.platform.trafficanalytics.domain.model.aggregates.HeatmapMetric;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.CreateHeatmapMetricCommand;
import com.retailpulse.platform.trafficanalytics.domain.model.commands.UpdateHeatmapMetricCommand;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetAllHeatmapMetricsQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetHeatmapMetricByIdQuery;
import com.retailpulse.platform.trafficanalytics.domain.model.queries.GetHeatmapMetricsByZoneIdQuery;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.List;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HeatmapMetricsControllerTest {
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new HeatmapMetricsController(new FakeCommandService(), new FakeQueryService())).build();

    @Test
    void getAllHeatmapMetricsReturnsOk() throws Exception {
        mockMvc.perform(get("/api/v1/heatmap-metrics")).andExpect(status().isOk());
    }

    private static class FakeQueryService implements HeatmapMetricQueryService {
        public List<HeatmapMetric> handle(GetAllHeatmapMetricsQuery query) {
            return List.of(new HeatmapMetric("HM001", "Z001", 340, 18, 12.0, 82, false));
        }

        public Optional<HeatmapMetric> handle(GetHeatmapMetricByIdQuery query) {
            return Optional.empty();
        }

        public List<HeatmapMetric> handle(GetHeatmapMetricsByZoneIdQuery query) {
            return List.of();
        }
    }

    private static class FakeCommandService implements HeatmapMetricCommandService {
        public Result<HeatmapMetric> handle(CreateHeatmapMetricCommand command) {
            return null;
        }

        public Result<HeatmapMetric> handle(UpdateHeatmapMetricCommand command) {
            return null;
        }
    }
}
