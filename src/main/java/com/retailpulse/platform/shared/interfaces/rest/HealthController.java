package com.retailpulse.platform.shared.interfaces.rest;

import com.retailpulse.platform.shared.interfaces.rest.resources.MessageResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
@Tag(name = "Health")
public class HealthController {
    @GetMapping
    @Operation(summary = "Check API health")
    public MessageResource health() {
        return new MessageResource("RetailPulse Platform API is running");
    }
}
