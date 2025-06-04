# Start from an OpenJDK image
FROM eclipse-temurin:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy your Maven wrapper and project files
COPY . .

# Package the application
RUN mvn clean install

# Expose port 8080
EXPOSE 8080

# Run the Spring Boot app
CMD ["java", "-jar", "target/hds-backend-0.0.1-SNAPSHOT.jar"]
