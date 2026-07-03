# Database Migrations

## Current state

The project currently relies on Hibernate schema generation through:

```text
JPA_DDL_AUTO=update
```

This is acceptable for an academic demo deployment because the application can create or update the PostgreSQL schema on startup.

## Production recommendation

For a production-like delivery, replace automatic schema updates with Flyway or Liquibase migrations and use:

```text
SPRING_PROFILES_ACTIVE=prod
JPA_DDL_AUTO=validate
```

The final academic deployment can use `SPRING_PROFILES_ACTIVE=prod,seed` to load demo data while keeping production-oriented runtime settings.
