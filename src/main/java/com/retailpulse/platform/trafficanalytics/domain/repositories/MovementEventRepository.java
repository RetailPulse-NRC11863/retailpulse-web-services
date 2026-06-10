package com.retailpulse.platform.trafficanalytics.domain.repositories;

import com.retailpulse.platform.trafficanalytics.domain.model.entities.MovementEvent;

public interface MovementEventRepository {
    MovementEvent save(MovementEvent event);
    long count();
}
