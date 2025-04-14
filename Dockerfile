# Use a lightweight base image
FROM public.ecr.aws/docker/library/amazoncorretto:21

# Set working directory
WORKDIR /app

# Copy the application JAR file
COPY target/zbfs-publisher-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]