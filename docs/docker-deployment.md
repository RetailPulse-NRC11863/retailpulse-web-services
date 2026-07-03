# Docker Deployment

## Build the image

```bash
docker build -t retailpulse-backend:local .
```

## Run locally

```bash
docker compose up --build
```

Local URLs:

```text
http://localhost:8080/api/v1/health
http://localhost:8080/swagger-ui/index.html
```

PostgreSQL is exposed to the host on:

```text
localhost:5433
```

## Stop local containers

```bash
docker compose down
```

To remove the local PostgreSQL volume:

```bash
docker compose down -v
```

## Environment variables

Required in Azure:

```text
SPRING_PROFILES_ACTIVE=prod,seed
WEBSITES_PORT=8080
PORT=8080
DB_URL=jdbc:postgresql://<server>.postgres.database.azure.com:5432/retailpulse?sslmode=require
DB_USERNAME=<database-user>
DB_PASSWORD=<database-password>
CORS_ALLOWED_ORIGINS=https://polite-sea-0e075210f.7.azurestaticapps.net
JPA_DDL_AUTO=update
```
