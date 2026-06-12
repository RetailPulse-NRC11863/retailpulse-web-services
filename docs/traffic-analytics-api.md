# Traffic Analytics API

## Purpose
This bounded context provides store layout zones and heatmap metrics for the RetailPulse admin dashboard.

## Base path
`/api/v1`

## Official endpoints
`GET /zones`
`GET /zones/{zoneId}`
`POST /zones`
`PUT /zones/{zoneId}`

`GET /layouts`
`GET /layouts/current`
`GET /layouts/{layoutId}`
`POST /layouts`
`PUT /layouts/{layoutId}`

`GET /heatmap-metrics`
`GET /heatmap-metrics/{metricId}`
`GET /heatmap-metrics/by-zone/{zoneId}`
`POST /heatmap-metrics`
`PUT /heatmap-metrics/{metricId}`

## Temporary compatibility endpoints
`GET /storeLayoutZones`
`GET /heatmapMetrics`

## Frontend migration
Before JSON Server:
- `/storeLayoutZones`
- `/heatmapMetrics`

Now Spring Boot official endpoints:
- `/zones`
- `/heatmap-metrics`
- `/layouts/current`

## Expected Angular use
Admin heatmap/layout views should consume:
- `GET /api/v1/layouts/current`

or:
- `GET /api/v1/zones`
- `GET /api/v1/heatmap-metrics`
