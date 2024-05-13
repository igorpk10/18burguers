FROM maven:3.8.4-openjdk-17-slim AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

RUN adduser --disabled-password --gecos '' appuser

USER appuser

COPY src ./src

RUN mvn package

FROM openjdk:17-slim

WORKDIR /app

COPY --from=builder /app/target/app.jar .

CMD ["java", "-jar", "app.jar"]