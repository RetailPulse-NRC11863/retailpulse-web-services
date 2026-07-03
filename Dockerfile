FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /workspace

COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

COPY src ./src
RUN mvn -q -DskipTests clean package

FROM eclipse-temurin:21-jre

WORKDIR /app

RUN useradd --create-home --shell /usr/sbin/nologin appuser

COPY --from=build /workspace/target/platform-0.0.1-SNAPSHOT.jar /app/app.jar

ENV PORT=8080

EXPOSE 8080

USER appuser

ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75.0", "-jar", "/app/app.jar"]
