# RetailPulse Web Services

## Description
RESTful API for RetailPulse platform.

## Tech Stack
- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Swagger / OpenAPI
- Maven

## Architecture
The project follows a DDD-inspired modular architecture using bounded contexts:
- Shared
- Store Foundation
- Inventory Intelligence
- Assisted Shopping
- Traffic Analytics
- Store Operations
- Promotion Optimization
- Subscription

## Run locally
mvn spring-boot:run

## Swagger
http://localhost:8080/swagger-ui/index.html

## Health Check
http://localhost:8080/api/v1/health

## Traffic Analytics
This module exposes the first real Spring Boot web services replacing JSON Server for the admin heatmap and layout views.

Official endpoints:
- GET /api/v1/zones
- GET /api/v1/layouts/current
- GET /api/v1/heatmap-metrics

Swagger:
http://localhost:8080/swagger-ui/index.html
