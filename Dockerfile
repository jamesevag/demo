# Use an official Maven image from Docker Hub
FROM docker.io/library/maven:3.8.6-eclipse-temurin-17 AS build

# Set working directory inside the container
WORKDIR /app

# ✅ Cache Maven dependencies
COPY pom.xml ./
COPY .mvn .mvn
COPY mvnw mvnw
RUN mvn dependency:go-offline -B

# ✅ Copy source code AFTER dependencies to optimize caching
COPY src ./src

# ✅ Clean and build JAR
RUN mvn clean package -DskipTests

# ✅ Use a lightweight JDK
FROM docker.io/library/eclipse-temurin:17-jdk-alpine
WORKDIR /app

# ✅ Copy built JAR with correct path
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# ✅ Expose the application port
EXPOSE 8080

# ✅ Use ENTRYPOINT (No CMD needed)
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=local", "/app/app.jar"]
