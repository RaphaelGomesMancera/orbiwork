FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /build

COPY pom.xml .
RUN mvn -q dependency:go-offline

COPY src ./src
RUN mvn -q package -DskipTests


# ----------------------
# RUNTIME IMAGE
# ----------------------
FROM registry.access.redhat.com/ubi9/openjdk-21:1.23

WORKDIR /app

COPY --from=build /build/target/quarkus-app/lib/ ./lib/
COPY --from=build /build/target/quarkus-app/*.jar ./
COPY --from=build /build/target/quarkus-app/app/ ./app/
COPY --from=build /build/target/quarkus-app/quarkus/ ./quarkus/

EXPOSE 8080

# Executa o JAR correto
ENTRYPOINT ["java", "-jar", "quarkus-run.jar"]
