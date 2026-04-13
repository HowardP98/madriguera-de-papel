# ===== Build stage =====
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copiamos pom primero para cachear dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el resto del código
COPY src ./src

# Construimos el jar
RUN mvn clean package -DskipTests

# ===== Run stage =====
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copiamos el jar generado
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
