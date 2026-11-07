FROM maven:3.9.9-eclipse-temurin-11 AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests package

FROM eclipse-temurin:11-jre
WORKDIR /app
COPY --from=build /workspace/target/sake-compass-1.0.0.jar /app/app.jar
EXPOSE 8080
CMD ["sh", "-c", "java -XX:MaxRAMPercentage=75 -jar /app/app.jar --server.port=${PORT:-8080}"]

