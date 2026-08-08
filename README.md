# RetailPulse Web Services

RetailPulse Web Services is the Spring Boot REST API for a retail intelligence and store-operations platform. It models the core behavior of a physical store: products are placed in zones, inventory changes generate operational signals, shopper activity produces traffic metrics, and conversion data can lead to promotion recommendations.

The API is organized around business contexts and exposes resources for the administrator dashboard, store configuration, inventory, assisted shopping, staff operations, promotion optimization and subscription management.

![Java](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0-6DB33F?logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?logo=apachemaven&logoColor=white)

## Domain overview

RetailPulse is designed around a continuous retail feedback loop:

```text
Shopper activity
      ↓
Traffic and conversion metrics
      ↓
Performance opportunities and recommendations
      ↓
Operational alerts and staff tasks
      ↓
Store configuration and inventory updates
```

The current dataset is a controlled demonstration scenario. It includes simulated store activity rather than a connection to physical sensors, but the API provides a movement-event endpoint that can support a future ingestion adapter.

## Bounded contexts

The backend follows a DDD-inspired modular structure. Each context contains domain models, repository contracts, application services, persistence adapters and REST resources where required.

| Context | Responsibility | Main concepts |
| --- | --- | --- |
| Store Foundation | Model the physical retail environment | Stores, zones, products, SKUs, placement and product status |
| Inventory Intelligence | Track stock and critical thresholds | Inventory items, stock status, automatic operational signals |
| Traffic Analytics | Represent shopper movement and zone behavior | Movement events, traffic, dwell time, interactions, heat and congestion |
| Assisted Shopping | Support product search from a kiosk | Kiosk sessions, searches, product location, shopper assistance |
| Store Operations | Turn signals into work for staff | Alerts, tasks, priorities, statuses and resolution/completion actions |
| Promotion Optimization | Identify conversion opportunities | Product opportunities, recommendations, priorities and application flow |
| Subscription | Represent SaaS commercial access | Plans, accounts, billing period, status and plan changes |
| Shared | Cross-cutting API and infrastructure concerns | Results, errors, auditing, CORS, OpenAPI and exception handling |

## Architecture

The service separates HTTP transport, application use cases, domain behavior and persistence concerns:

### Layer responsibilities

- `interfaces/rest`: Controllers, request resources, response resources and assemblers.
- `application`: Command services, query services and use-case orchestration.
- `domain`: Aggregates, entities, commands, queries, repositories and value objects.
- `infrastructure/persistence`: Spring Data JPA entities, repositories, adapters and domain/persistence assemblers.
- `shared`: Common result types, error responses, global exception handling, CORS, OpenAPI and initialization.

The API uses domain repository interfaces instead of coupling application services directly to Spring Data persistence. Persistence entities are translated to and from domain objects through dedicated assemblers.

## Main capabilities

### Store foundation

- Create, list, read and update stores.
- Create, list, read, update and delete zones.
- Store zone geometry through `x`, `y`, `width`, `height` and capacity fields.
- Create, search, read, update and delete products.
- Associate products with categories, SKUs, zones and shelf placement.

### Inventory intelligence

- Create inventory records for products.
- Track available stock and critical thresholds.
- Calculate `AVAILABLE`, `LOW_STOCK` and `OUT_OF_STOCK` states.
- Update product stock through a dedicated endpoint.
- Create an operational alert and pending task when stock becomes critical.

### Traffic analytics

- Retrieve heatmap metrics by zone.
- Retrieve zone traffic metrics.
- Retrieve congestion indicators.
- Register movement events with event type and timestamp.
- Create or update zone metrics.

### Assisted shopping

- Start a kiosk session for a store.
- Search products by query.
- Retrieve a product enriched with stock, placement and promotion context.
- Register search actions such as searched, found and location viewed.
- Create an assistance alert and task when a shopper requests help.

### Store operations

- Retrieve all or active operational alerts.
- Resolve alerts.
- Retrieve all or pending operational tasks.
- Complete tasks.
- Preserve source, trigger reason, priority, product and zone context for follow-up.

### Promotion optimization

- Retrieve active and historical recommendations.
- Retrieve product-performance opportunities.
- Create recommendations.
- Apply a recommendation and update its state.
- Connect traffic, dwell time and conversion indicators with commercial actions.

### Subscription management

- List and retrieve subscription plans.
- Retrieve the current SaaS account.
- Create accounts and change plans.
- Represent plan capabilities such as heatmap and conversion access in the frontend integration.

## REST API

All application endpoints are prefixed with `/api/v1`.

| Resource | Key endpoints | Purpose |
| --- | --- | --- |
| Health | `GET /health` | Service health check |
| Stores | `/stores` | Store profiles and managers |
| Zones | `/zones` | Store layout and zone geometry |
| Products | `/products` | Product catalog and search |
| Inventory | `/inventory/items` | Stock records and critical inventory |
| Traffic | `/traffic` | Heatmap, metrics, congestion and movement events |
| Kiosk | `/kiosk` | Product search, sessions and shopper actions |
| Alerts | `/operational-alerts` | Operational alerts and resolution |
| Tasks | `/operational-tasks` | Staff tasks and completion |
| Recommendations | `/promotion-recommendations` | Product opportunities and recommendations |
| Subscription plans | `/subscription/plans` | Available SaaS plans |
| SaaS accounts | `/subscription/accounts` | Current account and plan changes |

The API publishes an OpenAPI contract through Springdoc:

```text
http://localhost:8080/swagger-ui/index.html
http://localhost:8080/v3/api-docs
```

### Store context

The current frontend sends the `X-Owner-Email` header for requests that need to resolve the active SaaS account and store. Store-scoped read operations use that context to filter products, zones, inventory, traffic, recommendations and operational data.

The current project demonstrates this context mechanism but does not include a production identity provider or a complete authentication/authorization layer.

## Demo data initialization

`DataInitializer` runs when the `dev` or `seed` Spring profile is active. It creates a consistent demonstration scenario without requiring manual data entry:

- One retail store and account.
- Nine store zones with layout coordinates and types.
- Twenty-four products distributed across store zones.
- Inventory records with available, low-stock and out-of-stock examples.
- Zone traffic, dwell time, interaction, conversion and congestion metrics.
- Operational alerts and pending staff tasks.
- Promotion recommendations and product opportunities.
- Subscription plans and an active SaaS account.
- Kiosk sessions and product-search activity.

The initializer uses lookup and ensure operations for the demo entities so the application can be restarted without intentionally duplicating the base dataset.

## Getting started with Docker

### Prerequisites

- Docker Desktop with Docker Compose.
- Git.

### Start PostgreSQL and the API

From the repository root:

```bash
docker compose up --build
```

The Compose stack provides:

| Service | Address |
| --- | --- |
| REST API | `http://localhost:8080` |
| Health check | `http://localhost:8080/api/v1/health` |
| Swagger UI | `http://localhost:8080/swagger-ui/index.html` |
| PostgreSQL from the host | `localhost:5433` |

The API container connects to PostgreSQL through the Compose service name. The `dev` profile enables Hibernate schema updates and demo initialization.

Stop the stack with:

```bash
docker compose down
```

To remove the local PostgreSQL volume and start from an empty database:

```bash
docker compose down -v
```

### Run without Docker

1. Create a PostgreSQL database named `retailpulse`.
2. Copy the local configuration template:

   ```powershell
   Copy-Item src/main/resources/application-local.example.properties src/main/resources/application-local.properties
   ```

3. Set the local PostgreSQL credentials in `application-local.properties`.
4. Start the service with the Maven wrapper:

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

The default local configuration expects PostgreSQL on `localhost:5432`. The Docker workflow uses host port `5433` instead.

### Useful Maven commands

```powershell
.\mvnw.cmd test
.\mvnw.cmd spring-boot:run
```

## Configuration

The main runtime properties are resolved through environment variables:

| Variable | Purpose | Default/demo behavior |
| --- | --- | --- |
| `SPRING_PROFILES_ACTIVE` | Active Spring profiles | Compose uses `dev` |
| `PORT` | HTTP port | `8080` |
| `DB_URL` | PostgreSQL JDBC URL | Local PostgreSQL URL |
| `DB_USERNAME` | PostgreSQL user | `postgres` for native local setup |
| `DB_PASSWORD` | PostgreSQL password | Set locally; never commit real credentials |
| `JPA_DDL_AUTO` | Hibernate schema strategy | `update` in demo/dev, `validate` in prod |
| `CORS_ALLOWED_ORIGINS` | Allowed frontend origins | Local Angular origin plus configured deployment origin |

The repository intentionally keeps production database credentials outside Git. Use `application-local.properties` or deployment environment variables for local and hosted environments.

## Database and migrations

The current project uses Hibernate schema generation with `JPA_DDL_AUTO=update` for the demonstration deployment. Flyway or Liquibase migrations are not included yet.

For a production-oriented setup, the recommended direction is:

1. Add versioned Flyway or Liquibase migrations.
2. Run production with `JPA_DDL_AUTO=validate`.
3. Keep demo initialization behind an explicit `seed` profile.

## Repository structure

```text
.
├── src/main/java/com/retailpulse/platform/
│   ├── assistedshopping/
│   ├── inventoryintelligence/
│   ├── promotionoptimization/
│   ├── shared/
│   ├── storefoundation/
│   ├── storeoperations/
│   ├── subscription/
│   └── trafficanalytics/
├── src/main/resources/
│   ├── application.properties
│   ├── application-dev.properties
│   ├── application-prod.properties
│   └── application-local.example.properties
├── src/test/
├── docs/
│   ├── azure-app-service-deployment.md
│   ├── database-migrations.md
│   └── docker-deployment.md
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── mvnw / mvnw.cmd
```

## Current scope

RetailPulse Web Services currently provides the domain model, REST resources, persistence adapters, demo initialization and integration surface for a retail operations platform. The current scope does not include a production authentication provider, real sensor gateway, payment processor, event broker or versioned database migration system. These are clear extension points for a production delivery.
