# Stage 1: Build the Java Application (Upgraded to Java 21)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the Application (Upgraded to Java 21)
FROM eclipse-temurin:21-jre
WORKDIR /app
# We create an uploads folder inside the container so your ads have a place to save!
RUN mkdir uploads 
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]