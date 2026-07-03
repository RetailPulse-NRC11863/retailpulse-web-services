# Azure App Service Deployment

## Purpose

RetailPulse Web Services is prepared to be deployed to Azure App Service for Containers using an image published to Azure Container Registry.

## Build the Docker image

```bash
docker build -t retailpulse-backend:2.0.0 .
```

## Push to Azure Container Registry

```bash
az login
az acr login --name <acr-name>
docker tag retailpulse-backend:2.0.0 <acr-name>.azurecr.io/retailpulse-backend:2.0.0
docker push <acr-name>.azurecr.io/retailpulse-backend:2.0.0
```

## App Service container settings

Configure the Web App container image with:

```text
<acr-name>.azurecr.io/retailpulse-backend:2.0.0
```

Required App Service settings:

```text
SPRING_PROFILES_ACTIVE=prod,seed
WEBSITES_PORT=8080
PORT=8080
DB_URL=jdbc:postgresql://<server-name>.postgres.database.azure.com:5432/retailpulse?sslmode=require
DB_USERNAME=<database-user>
DB_PASSWORD=<database-password>
CORS_ALLOWED_ORIGINS=https://polite-sea-0e075210f.7.azurestaticapps.net
JPA_DDL_AUTO=update
```

`SPRING_PROFILES_ACTIVE=prod,seed` enables production logging/settings while loading the academic demo dataset. `JPA_DDL_AUTO=update` is acceptable for the final demo deployment because the current project does not include Flyway or Liquibase migrations.

## Health check

```text
/api/v1/health
```

## Swagger

```text
https://<app-service-name>.azurewebsites.net/swagger-ui/index.html
```
