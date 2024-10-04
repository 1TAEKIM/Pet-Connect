# Stage 1: Build the application using Gradle
FROM gradle:7.6.0-jdk17 AS build  
# JDK 17을 사용하는 Gradle 이미지

# Set the working directory inside the container
WORKDIR /home/gradle/project

# Copy the Gradle wrapper files and build.gradle files
COPY build.gradle settings.gradle ./

# Copy Gradle-related directories
COPY gradle ./gradle

# Download dependencies before copying the rest of the source code to leverage caching
RUN gradle build --no-daemon || return 0

# Copy the rest of the source code
COPY . .

# Build the application
RUN gradle build --no-daemon

# Stage 2: Create the final lightweight image
FROM openjdk:17-jdk-slim  
# JDK 17을 사용하는 경량 OpenJDK 이미지

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file from the previous stage
COPY --from=build /home/gradle/project/build/libs/*.jar /app/app.jar

# Expose the port the Spring Boot app will run on
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
