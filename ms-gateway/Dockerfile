FROM maven:3.8.5-openjdk-11 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar
EXPOSE 8080

ARG EUREKA_SERVER=localhost
ARG EUREKA_PORT=8761
ARG EUREKA_USER=eureka_user
ARG EUREKA_PASSWORD=eureka_password
ARG KEYCLOAK_SERVER=localhost
ARG KEYCLOAK_PORT=8081
ARG KEYCLOACK_REALM=ms-realm

ENTRYPOINT java -jar app.jar