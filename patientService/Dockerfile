FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src/ ./src
RUN mvn clean package -DskipTests


FROM openjdk:17-jdk AS runner

WORKDIR /app
COPY --from=builder /app/target/patientService-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 4000

ENTRYPOINT ["java", "-jar", "app.jar"]