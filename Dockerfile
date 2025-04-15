# Stage 1: Build the JAR using Gradle
#FROM gradle:8.4-jdk17 AS build
#WORKDIR /app
#COPY --chown=gradle:gradle . .
#RUN gradle build --no-daemon

# Stage 2: Run the app
#FROM openjdk:17-jdk-slim
#WORKDIR /app
#COPY --from=build /app/build/libs/*.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "app.jar"]


# Use Gradle image to build the app
FROM gradle:8.1.1-jdk17 AS build

# Create app directory
WORKDIR /app

# Copy source files
COPY . .

# Clean and build
RUN gradle clean build --no-daemon

# Second stage: smaller image to run the app
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy only the built jar from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the port your Spring Boot app listens to
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
