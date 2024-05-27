FROM maven:3.8.4-openjdk-17-slim AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package

FROM openjdk:17-slim

RUN adduser --disabled-password --gecos '' appuser

USER appuser

WORKDIR /app

COPY --from=builder /app/target/app.jar .

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]