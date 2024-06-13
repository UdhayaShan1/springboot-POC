# Use an official Gradle image to build the application
FROM gradle:7.6-jdk17 as builder
WORKDIR /home/gradle/project
COPY . .

# Build the application
RUN gradle clean build -x test

# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-slim-buster
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the shell script
ENTRYPOINT ["/app/run.sh"]
