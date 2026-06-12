# Traffic Analytics Curl Commands

```bash
curl http://localhost:8080/api/v1/health
```

```bash
curl http://localhost:8080/api/v1/zones
```

```bash
curl http://localhost:8080/api/v1/layouts/current
```

```bash
curl http://localhost:8080/api/v1/heatmap-metrics
```

```bash
curl -X POST http://localhost:8080/api/v1/zones \
  -H "Content-Type: application/json" \
  -d '{
    "id": "Z010",
    "name": "Seasonal Zone",
    "x": 500,
    "y": 360,
    "width": 180,
    "height": 80,
    "type": "PROMO"
  }'
```

```bash
curl -X PUT http://localhost:8080/api/v1/zones/Z010 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Seasonal Promo Zone",
    "x": 500,
    "y": 360,
    "width": 180,
    "height": 80,
    "type": "PROMO"
  }'
```

```bash
curl -X POST http://localhost:8080/api/v1/heatmap-metrics \
  -H "Content-Type: application/json" \
  -d '{
    "id": "HM010",
    "zoneId": "Z010",
    "traffic": 210,
    "averageDwellTimeSeconds": 38,
    "conversionRate": 24,
    "intensity": 64,
    "attentionRequired": false
  }'
```

```bash
curl -X PUT http://localhost:8080/api/v1/heatmap-metrics/HM010 \
  -H "Content-Type: application/json" \
  -d '{
    "zoneId": "Z010",
    "traffic": 260,
    "averageDwellTimeSeconds": 44,
    "conversionRate": 29,
    "intensity": 78,
    "attentionRequired": true
  }'
```
