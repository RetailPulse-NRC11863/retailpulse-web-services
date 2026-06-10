package com.retailpulse.platform.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI retailPulseOpenApi() {
        return new OpenAPI().info(new Info()
            .title("RetailPulse Platform")
            .description("RESTful API for RetailPulse platform.")
            .version("v1"));
    }
}
