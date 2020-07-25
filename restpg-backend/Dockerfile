FROM maven:3.6.3-openjdk-14 as build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:14-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=restpg-api/target/*.jar
COPY --from=build  /app/${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]