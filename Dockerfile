# ---- Build Stage ----
FROM gradle:8.3.0-jdk17 AS build

### Build arguments ###
ARG JAR_FILE=build/libs/*.jar
ARG APP_DIR=/usr/local/app
ARG APP_PROFILE
ARG GCP_SA_KEY_PATH
ARG GCP_ACCESS_TOKEN
ARG GCP_PROJECT_ID
ARG BQ_DATASET
ARG BQ_TABLE
###

### Environment variables ###
# OS
ENV APP_DIR=${APP_DIR}
# JVM arguments.
ENV APP_PROFILE=${APP_PROFILE}
ENV GCP_SA_KEY_PATH=${GCP_SA_KEY_PATH}
ENV GOOGLE_APPLICATION_CREDENTIALS=${GCP_SA_KEY_PATH}
ENV GCP_ACCESS_TOKEN=${GCP_ACCESS_TOKEN}
ENV GCP_PROJECT_ID=${GCP_PROJECT_ID}
ENV BQ_DATASET=${BQ_DATASET}
ENV BQ_TABLE=${BQ_TABLE}
###

# Set working directory
WORKDIR /app

# Copy the source code
COPY . .

# Build the application
RUN gradle clean bootJar

# ---- Run Stage ----
FROM openjdk:17-jdk-slim

# Set application port
EXPOSE 8080

# Set working directory
WORKDIR /app

# Copy the executable jar from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Run the application
CMD ["java", "-jar", "app.jar"]

