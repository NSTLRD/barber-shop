# Start with a base image that includes Java
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file created during the build to the container
COPY target/barber-shop-0.0.1-SNAPSHOT.jar barber-shop.jar

# Expose the port the app runs on
EXPOSE 8080

# Define the entry point to run the app
ENTRYPOINT ["java", "-jar", "barber-shop.jar"]
