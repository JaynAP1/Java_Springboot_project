FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the JAR file
COPY target/taller1-0.0.1-SNAPSHOT.jar app.jar

# Environment variable with a default value
ENV PORT=8080

# Expose the port that will be used at runtime
EXPOSE ${PORT}

# Health check to ensure the application is running properly
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD wget -qO- http://localhost:${PORT}/actuator/health || exit 1

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

