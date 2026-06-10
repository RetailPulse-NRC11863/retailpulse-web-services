package com.retailpulse.platform.trafficanalytics.interfaces.rest.resources;

import com.retailpulse.platform.trafficanalytics.domain.model.valueobjects.CongestionStatus;

public record CongestionMetricResource(Long zoneId, CongestionStatus congestionStatus, Integer trafficCount) {
}
