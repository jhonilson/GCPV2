# ════════════════════════════════════════════════════════
# STAGE 1 — Build
# ════════════════════════════════════════════════════════
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests -B

# ════════════════════════════════════════════════════════
# STAGE 2 — Runtime
# ════════════════════════════════════════════════════════
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

RUN addgroup -S cmall && adduser -S cmall -G cmall

COPY --from=builder /app/target/*.jar app.jar

RUN chown cmall:cmall app.jar

USER cmall

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=prod
# ← Agregar EnableVirtualThreads para Java 21
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -Djava.security.egd=file:/dev/./urandom"

HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD wget -qO- http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]